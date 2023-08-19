package com.casa.services;

import com.casa.domain.dtos.HorarioMostrarSimple;
import com.casa.domain.dtos.HorarioRegistroDto;
import com.casa.domain.entities.DiaEntity;
import com.casa.domain.entities.HorarioEntity;
import com.casa.domain.entities.HorasDiaCursoEntity;
import com.casa.domain.mappers.HorarioMapper;
import com.casa.repositories.HorarioRepository;
import com.casa.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private HorasDiaCursoService horasDiaCursoSvc;

    private Boolean validarCamposRegistrar(HorarioRegistroDto horario) {
        log.info("HorarioService.class - validarCamposRegistrar() -> Validando campos vacios...!");
        if(horario.getDia() == null || horario.getDia().getId() == null) {
            return true;
        }
        if(horario.getMateria() == null || horario.getMateria().getId() == null) {
            return true;
        }
        return horario.getProfesor() == null || horario.getProfesor().getId() == null;
    }

    private Boolean validarHorasPorDias(DiaEntity dia) {
        return dia.getHoras() <= horasDiaCursoSvc.sumaHorasDia(dia.getId());
    }

    private Boolean validarHorasIngresar(Integer horasIngresar) {
        if(horasIngresar < 1) {
            return true;
        }
        return horasIngresar >= 3;
    }

    public Map<String, Object> consultarPorIdDia(Long id) {
        Map<String, Object> map = new HashMap<>();
        List<HorarioMostrarSimple> listHorarioEntity = HorarioMapper.convertirListEntityToMostrarSimple(horarioRepository.findByIdDia(id));
        if(!listHorarioEntity.isEmpty()) {
            map.put(Constantes.MAP_RESPUESTA, listHorarioEntity);
        } else {
            map.put(Constantes.MAP_NOEXISTENTE, Constantes.MSG_NO_EXISTENTE);
        }
        return map;
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
            map.put(Constantes.MAP_NOEXISTENTE, Constantes.MSG_NO_EXISTENTE);
        }
        return map;
    }

    public Map<String, Object> registrar(HorarioRegistroDto horario) {
        log.info("HorarioService.class - registrar() -> Registrando horario...!");
        Map<String, Object> map = new HashMap<>();
        if(Boolean.TRUE.equals(validarCamposRegistrar(horario))) {
            map.put(Constantes.MAP_CAMPOSVACIOS, Constantes.MSG_CAMPOS_VACIOS);
            return map;
        }
        DiaEntity dia = diaSvc.consultarPorId( horario.getDia().getId() );
        if(dia == null) {
            map.put("errorDiaVacio", Constantes.MSG_NO_EXISTENTE);
            return map;
        }
        if(validarHorasPorDias(dia)) {
            map.put("errorHorasDias", "No se le puede agregar esta (" +horario.getHorasDictar()+ ") cantidad de horas a este dia");
            return map;
        }
        if(validarHorasIngresar(horario.getHorasDictar())) {
            map.put("errorHorasIngresar", "No se puede ingresar mas de 2 horas en el horario");
            return map;
        }
        if(materiaSvc.consultarPorId(horario.getMateria().getId()) == null) {
            map.put("errorMateriaVacia", Constantes.MSG_NO_EXISTENTE);
            return map;
        }
        if(profesorSvc.consultarPorId(horario.getProfesor().getId()) == null) {
            map.put("errorProfesorVacio", Constantes.MSG_NO_EXISTENTE);
        } else {
            horario.setFechaModificacion(Constantes.consultarFechaActual());
            horario.setFechaRegistro(Constantes.consultarFechaActual());
            map.put(Constantes.MAP_RESPUESTA, horarioRepository.save(HorarioMapper.convertirDtoAEntity(horario)).getId());

            horasDiaCursoSvc.registrar(new HorasDiaCursoEntity(null, dia, horario.getIdCurso(), horario.getHorasDictar(),
                    Constantes.consultarFechaActual(), Constantes.consultarFechaActual()));
        }
        return map;
    }

    public List<HorarioEntity> consultarTodos() {
        log.info("HorarioService.class - consultarTodos() -> Consultando todos los Horarios...!");
        return horarioRepository.findAll();
    }

}
