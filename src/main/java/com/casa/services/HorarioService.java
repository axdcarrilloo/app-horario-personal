package com.casa.services;

import com.casa.domain.dtos.HorarioMostrarSimple;
import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.entities.*;
import com.casa.domain.mappers.HorarioMapper;
import com.casa.repositories.HorarioRepository;
import com.casa.utils.Constantes;
import com.casa.utils.MensajesProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HorarioService {

    private final Logger log = LoggerFactory.getLogger(HorarioService.class);

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DiaService diaSvc;

    @Autowired
    private MateriaService materiaSvc;

    @Autowired
    private ProfesorService profesorSvc;

    @Autowired
    private HorasDiaService horasDiaSvc;

    private Boolean validarCamposRegistrar(HorarioRegistroDto horario) {
        log.info("HorarioService.class - validarCamposRegistrar() -> Validando campos vacios...!");
        if(horario.getDia() == null || horario.getDia().getId() == null) {
            return true;
        }
        if(horario.getMateria() == null || horario.getMateria().getId() == null) {
            return true;
        }
        if(horario.getIdCurso() == null) {
            return true;
        }
        return horario.getProfesor() == null || horario.getProfesor().getId() == null;
    }

    private Integer sacarHorasCursoPorDia(DiaEntity dia, Long idCurso) {
        log.info("HorarioService.class - sacarHorasCursoPorDia() -> Sacando horas de curso por dia...!");
        List<HorarioEntity> horarios = consultarPorCurso(idCurso);
        List<HorasDiaEntity> horasDia = new ArrayList<>();
        int sumarHorasClases = 0;
        for(HorarioEntity horario : horarios) {
            horasDia.addAll(horasDiaSvc.consultarPorHorarioYDia(horario, dia));
        }
        for(HorasDiaEntity horaDia : horasDia) {
            sumarHorasClases += horaDia.getCantidadHoras();
        }
        return sumarHorasClases;
    }

    private Boolean validarHorasPorDia(DiaEntity dia, Integer horasDictar, Long idCurso) {
        log.info("HorarioService.class - validarHorasPorDias() -> Validando horas por dia...!");
        return dia.getHoras() < (sacarHorasCursoPorDia(dia, idCurso)  + horasDictar);
    }

    private Boolean validarHorasIngresar(Integer horasIngresar) {
        log.info("HorarioService.class - validarHorasIngresar() -> Validando cantidad de horas a ingresar...!");
        if(horasIngresar < 1) {
            return true;
        }
        return horasIngresar >= 3;
    }

    private List<HorasDiaEntity> sacarHorasDiaDeHorarios(List<HorarioEntity> horarios) {
        List<HorasDiaEntity> horasDia = new ArrayList<>();
        for(HorarioEntity horario : horarios) {
            horasDia.addAll(horasDiaSvc.consultarPorHorario(horario));
        }
        return horasDia;
    }

    public Boolean validarDuplicidadMateriaCurso(MateriaEntity materia, Long idCurso, DiaEntity dia, Integer horasDictar) {
        List<HorarioEntity> horarios = horarioRepository.findByMateriaAndIdCurso(materia, idCurso);
        List<HorasDiaEntity> horasDia = sacarHorasDiaDeHorarios(horarios);
        int sumatoriaHoras = 0;
        for(HorasDiaEntity horaDia : horasDia) {
            if(Objects.equals(horaDia.getDia(), dia)) {
                sumatoriaHoras += (horaDia.getCantidadHoras() + horasDictar);
            }
        }
        return sumatoriaHoras > 2;
    }

    public List<HorarioMostrarSimple> consultarTodosSimplificado() {
        List<HorarioEntity> horarios = consultarTodos();
        return HorarioMapper.convertirEntityADto(sacarHorasDiaDeHorarios(horarios));
    }

    public Map<String, Object> consultarPorDia(Long idDia) {
        log.info("HorarioService.class - consultarPorDia() -> Consultando por Dia...!");
        Map<String, Object> map = new HashMap<>();
        DiaEntity dia = diaSvc.consultarPorId(idDia);
        if(dia != null) {
            List<HorasDiaEntity> horasDia = horasDiaSvc.consultarPorDia(dia);
            List<HorarioEntity> horarios = new ArrayList<>();
            for(HorasDiaEntity horadia : horasDia) {
                horarios.add(horadia.getHorario());
            }
            map.put(Constantes.MAP_RESPUESTA, horarios);
        } else {
            map.put("errorDiaVacio", MensajesProperties.MSG_NO_EXISTENTE);
        }

        return map;
    }

    public List<HorarioEntity> consultarPorCurso(Long idCurso) {
        log.info("HorarioService.class - consultarPorCurso() -> Consultando por curso...!");
        return horarioRepository.findByIdCurso(idCurso);
    }

    public Boolean existenciaPorProfesorMateriaCurso(ProfesorEntity profesor, MateriaEntity materia, Long idCurso) {
        log.info("HorarioService.class - existenciaPorProfesorMateriaCurso() -> Validando existencia de horario por profesor, materia y curso...!");
        return consultarPorProfesorMateriaCurso(profesor, materia, idCurso) != null;
    }

    public HorarioEntity consultarPorProfesorMateriaCurso(ProfesorEntity profesor, MateriaEntity materia, Long idCurso) {
        log.info("HorarioService.class - consultarPorProfesorMateriaCurso() -> Consultando por profesor, materia y curso...!");
        return horarioRepository.findByProfesorAndMateriaAndIdCurso(profesor, materia,idCurso);
    }

    public HorarioEntity consultarPorId(Long id) {
        log.info("HorarioService.class - consultarPorId() -> Consultando por Id...!");
        Optional<HorarioEntity> optional = horarioRepository.findById(id);
        return optional.orElse(null);
    }

    public Boolean existenciaPorId(Long id) {
        log.info("HorarioService.class - existenciaPorId() -> Validando existencia...!");
        return consultarPorId(id) != null;
    }

    public Map<String, Object> eliminar(Long id) {
        log.info("HorarioService.class - eliminar() -> Eliminando horario...!");
        Map<String, Object> map = new HashMap<>();
        if(Boolean.TRUE.equals(existenciaPorId(id))) {
            horarioRepository.deleteById(id);
            map.put(Constantes.MAP_RESPUESTA, id);
        } else {
            map.put(Constantes.MAP_ERROR_NOEXISTENTE, MensajesProperties.MSG_NO_EXISTENTE);
        }
        return map;
    }

    public Map<String, Object> registrar(HorarioRegistroDto horario) {
        Map<String, Object> map = new HashMap<>();
        if(Boolean.TRUE.equals(validarCamposRegistrar(horario))) {
            map.put(Constantes.MAP_ERROR_CAMPOSVACIOS, MensajesProperties.MSG_CAMPOS_VACIOS);
            return map;
        }
        DiaEntity dia = diaSvc.consultarPorId( horario.getDia().getId() );
        if(dia == null) {
            map.put("errorDiaVacio", MensajesProperties.MSG_NO_EXISTENTE);
            return map;
        }
        if(validarHorasPorDia(dia, horario.getHorasDictar(), horario.getIdCurso())) {
            map.put("errorHorasDias", "No se le puede agregar esta (" +horario.getHorasDictar()+ ") cantidad de horas a este dia");
            return map;
        }
        if(validarHorasIngresar(horario.getHorasDictar())) {
            map.put("errorHorasIngresar", "No se puede ingresar mas de 2 horas en el horario");
            return map;
        }
        ProfesorEntity profesor = profesorSvc.consultarPorId(horario.getProfesor().getId());
        if(profesor == null) {
            map.put("errorProfesorVacio", MensajesProperties.MSG_NO_EXISTENTE);
            return map;
        }
        MateriaEntity materia = materiaSvc.consultarPorId(horario.getMateria().getId());
        if(materia == null) {
            map.put("errorMateriaVacia", MensajesProperties.MSG_NO_EXISTENTE);
            return map;
        }
        if(existenciaPorProfesorMateriaCurso(profesor, materia, horario.getIdCurso())) {
            map.put("errorDuplicidad", MensajesProperties.MSG_SI_EXISTENTE);
            return map;
        }
        if(validarDuplicidadMateriaCurso(materia, horario.getIdCurso(), dia, horario.getHorasDictar())){
            map.put("errorDuplicidadMateria", "Ya este curso dara la clase de "+materia.getNombre()+" este dia "+dia.getNombre());
        }else {
            log.info("HorarioService.class - registrar() -> Registrando horario...!");
            HorarioEntity horarioPrincipal = horarioRepository.save(HorarioMapper.convertirDtoAEntity(horario));
            map.put(Constantes.MAP_RESPUESTA, horarioPrincipal.getId());
            horasDiaSvc.registrar(new HorasDiaEntity(0L, dia, horarioPrincipal, horario.getHorasDictar(),
                    Constantes.consultarFechaActual(), Constantes.consultarFechaActual()));
        }
        return map;
    }

    public List<HorarioEntity> consultarTodos() {
        log.info("HorarioService.class - consultarTodos() -> Consultando todos los Horarios...!");
        return horarioRepository.findAll();
    }

}
