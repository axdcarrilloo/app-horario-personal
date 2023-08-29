package com.casa.utils;

public class Route {
	
	private Route() {}
	
	public static final String DIA = "/Dia";
	public static final String MATERIA = "/Materia";
	public static final String PROFESOR = "/Profesor";
	public static final String ANNO = "/Año";
	public static final String MES = "/Mes";
	public static final String SEMANA = "/Semana";
	public static final String CURSO = "/Curso";
	public static final String HORARIO = "/Horario";
	
	public static final String CONSULTAR_TOS = "/ConsultarTodos";
	public static final String CONSULTAR_TAS = "/ConsultarTodas";
	public static final String REGISTRAR = "/Registrar";
	public static final String MODIFICAR = "/Modificar";
	public static final String CONSULTARPOR_NOMBRE = "/ConsultarPorNombre/{nombre}";
	public static final String ELIMINAR_PORID = "/Eliminar/{id}";
	public static final String CONSULTARPOR_IDDIA = "/ConsultarPorIdDia/{id}";
	public static final String CONSULTARPOR_IDCURSO = "/ConsultarPorIdCurso/{id}";
	public static final String CONSULTAR_TOS_SIMPLIFICADO = "/ConsultarTodosSimplificado";


}

