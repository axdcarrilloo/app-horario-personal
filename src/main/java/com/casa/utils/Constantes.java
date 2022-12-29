package com.casa.utils;

import java.time.LocalDateTime;

public class Constantes {
	
	private Constantes() {}
	
	public static final String TTL_REGISTRO_EXITOSO = "Registro Exitoso";
	public static final String TTL_REGISTRO_FALLIDO = "Registro Fallido";
	public static final String TTL_MODIFICACION_EXITOSA = "Modificacion Exitosa";
	public static final String TTL_MODIFICACION_FALLIDA = "Modificacion Fallida";
	public static final String TTL_CONSULTA_EXITOSA = "Consulta Exitosa";
	
	public static final String MSG_CAMPOS_VACIOS = "Campos vacios, favor validar";
	public static final String MSG_NO_EXISTENTE = "No se encuentra";
	
	public static LocalDateTime consultarFechaActual() {
		return LocalDateTime.now();
	}

}
