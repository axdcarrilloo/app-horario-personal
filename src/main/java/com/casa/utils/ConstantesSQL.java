package com.casa.utils;

public class ConstantesSQL {
	
	private ConstantesSQL() {}
	
	public static final String MODIFICAR_PROFESOR = "UPDATE `profesores` SET `cedula` = :cedula, `nombres` = :nombres, `apellidos` = :apellidos, `edad` = :edad,"
			+ "`celular` = :celular, `direccion` = :direccion, `email` = :email, `fecha_modificacion` = :fechaModificacion WHERE `profesores`.`id` = :id";
	
	public static final String MODIFICAR_MATERIA = "UPDATE `materias` SET `nombre` = :nombre, "
			+ "`fecha_modificacion` = :fechaModificacion WHERE `materias`.`id` = :id";
	
	public static final String MODIFICAR_DIA = "UPDATE `dias` SET `nombre` = :nombre, "
			+ "`fecha_modificacion` = :fechaModificacion WHERE `dias`.`id` = :id";

	public static final String CONSULTARPOR_IDDIA = "SELECT * FROM `horas_dia` hd WHERE hd.id_dia = :idDia";

}
