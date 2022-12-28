package com.casa.utils;

public class ConstantesSQL {
	
	private ConstantesSQL() {}
	
	public static final String MODIFICAR_DIA = "UPDATE `dias` SET `nombre` = :nombre, "
			+ "`fecha_modificacion` = :fechaModificacion WHERE `dias`.`id` = :id";

}
