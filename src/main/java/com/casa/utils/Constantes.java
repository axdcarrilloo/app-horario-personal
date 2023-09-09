package com.casa.utils;

import java.time.LocalDateTime;

public class Constantes {
	
	private Constantes() {}

	public static final String MAP_RESPUESTA = "respuesta";
	public static final String MAP_ERROR_NOEXISTENTE = "errorNoExistente";
	public static final String MAP_ERROR_SIEXISTENTE = "errorSiExiste";
	public static final String MAP_ERROR_CAMPOSVACIOS = "errorCamposVacios";
	public static final String MAP_ERROR_DUPLICIDAD = "errorDuplicidad";
	
	public static LocalDateTime consultarFechaActual() {
		return LocalDateTime.now();
	}

}
