package com.casa.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MensajesProperties {

    public static String MSG_SI_EXISTENTE;
    public static String MSG_CAMPOS_VACIOS;
    public static String MSG_NO_EXISTENTE;
    public static String TTL_ELIMINACION_FALLIDA;
    public static String TTL_ELIMINACION_EXITOSA;
    public static String TTL_CONSULTA_FALLIDA;
    public static String TTL_CONSULTA_EXITOSA;
    public static String TTL_MODIFICACION_FALLIDA;
    public static String TTL_MODIFICACION_EXITOSA;
    public static String TTL_REGISTRO_FALLIDO;
    public static String TTL_REGISTRO_EXITOSO;

    @Value("${ttl.registro.exitoso}")
    public void setTTL_REGISTRO_EXITOSO(String mensaje) {TTL_REGISTRO_EXITOSO = mensaje;}
    @Value("${ttl.registro.fallido}")
    public void setTTL_REGISTRO_FALLIDO(String mensaje) {TTL_REGISTRO_FALLIDO = mensaje;}
    @Value("${ttl.modificacion.exitosa}")
    public void setTTL_MODIFICACION_EXITOSA(String mensaje) {TTL_MODIFICACION_EXITOSA = mensaje;}
    @Value("${ttl.modificacion.fallida}")
    public void setTTL_MODIFICACION_FALLIDA(String mensaje) {TTL_MODIFICACION_FALLIDA = mensaje;}
    @Value("${ttl.consulta.exitosa}")
    public void setTTL_CONSULTA_EXITOSA(String mensaje) {TTL_CONSULTA_EXITOSA = mensaje;}
    @Value("${ttl.consulta.fallida}")
    public void setTTL_CONSULTA_FALLIDA(String mensaje) {TTL_CONSULTA_FALLIDA = mensaje;}
    @Value("${ttl.eliminacion.exitosa}")
    public void setTTL_ELIMINACION_EXITOSA(String mensaje) {TTL_ELIMINACION_EXITOSA = mensaje;}
    @Value("${ttl.eliminacion.fallida}")
    public void setTTL_ELIMINACION_FALLIDA(String mensaje) {TTL_ELIMINACION_FALLIDA = mensaje;}
    @Value("${msg.no.existente}")
    public void setMSG_NO_EXISTENTE(String mensaje) {MSG_NO_EXISTENTE = mensaje;}
    @Value("${msg.campos.vacios}")
    public void setMSG_CAMPOS_VACIOS(String mensaje) {MSG_CAMPOS_VACIOS = mensaje;}
    @Value("${msg.si.existente}")
    public void setMSG_SI_EXISTENTE(String mensaje) {MSG_SI_EXISTENTE = mensaje;}
}
