--Años
INSERT INTO `años` (`actual`, `anno`, `fecha_modificacion`, `fecha_registro`, `nombre`) VALUES (b'0', '2017', '2020-04-26 18:17:06.000000', '2020-04-26 18:17:06.000000', '2017');
INSERT INTO `años` (`actual`, `anno`, `fecha_modificacion`, `fecha_registro`, `nombre`) VALUES (b'0', '2018', '2020-04-26 18:17:06.000000', '2020-04-26 18:17:06.000000', '2018');

--Meses
INSERT INTO `meses` (`fecha_modificacion`, `fecha_registro`, `nombre`, `numero_mes_anno`, `id_anno`) VALUES ('2020-04-26 18:22:57.000000', '2020-04-26 18:22:57.000000', 'Enero', '1', '1');
INSERT INTO `meses` (`fecha_modificacion`, `fecha_registro`, `nombre`, `numero_mes_anno`, `id_anno`) VALUES ('2020-04-26 18:22:57.000000', '2020-04-26 18:22:57.000000', 'Febrero', '2', '1');

--Semanas
INSERT INTO `semanas` (`fecha_modificacion`, `fecha_registro`, `nombre`, `numero_semana_mes`, `id_mes`) VALUES ('2020-04-26 18:25:25.000000', '2020-04-26 18:25:25.000000', '1ra', '1', '1');

--Dias
INSERT INTO `dias` (`fecha_modificacion`, `fecha_registro`, `horas`, `horas_acumuladas`, `nombre`, `id_semana`) VALUES ('2020-04-26 18:31:47.000000', '2023-04-26 18:31:47.000000', '7', '0', 'Lunes', '1');

--Materias
INSERT INTO `materias` (`fecha_modificacion`, `fecha_registro`, `nombre`) VALUES ('2020-04-26 19:11:10.000000', '2020-04-26 19:11:10.000000', 'Castellano');
INSERT INTO `materias` (`fecha_modificacion`, `fecha_registro`, `nombre`) VALUES ('2020-04-26 19:11:10.000000', '2020-04-26 19:11:10.000000', 'Matematicas');
INSERT INTO `materias` (`fecha_modificacion`, `fecha_registro`, `nombre`) VALUES ('2020-04-26 19:11:10.000000', '2020-04-26 19:11:10.000000', 'Sociales');
INSERT INTO `materias` (`fecha_modificacion`, `fecha_registro`, `nombre`) VALUES ('2020-04-26 19:11:10.000000', '2020-04-26 19:11:10.000000', 'Religion');
INSERT INTO `materias` (`fecha_modificacion`, `fecha_registro`, `nombre`) VALUES ('2020-04-26 19:11:10.000000', '2020-04-26 19:11:10.000000', 'Ingles');
INSERT INTO `materias` (`fecha_modificacion`, `fecha_registro`, `nombre`) VALUES ('2020-04-26 19:11:10.000000', '2020-04-26 19:11:10.000000', 'Fisica');

--Profesores
INSERT INTO `profesores` (`apellidos`, `cedula`, `celular`, `direccion`, `edad`, `email`, `fecha_modificacion`, `fecha_registro`, `nombres`) VALUES ('Julio Bolaño', '963258', '3997419874', 'Clle la Paz #74-93', '27', 'ju_12@correo.com', '2020-04-26 19:12:57.000000', '2020-04-26 19:12:57.000000', 'Maria Antonieta');
INSERT INTO `profesores` (`apellidos`, `cedula`, `celular`, `direccion`, `edad`, `email`, `fecha_modificacion`, `fecha_registro`, `nombres`) VALUES ('Valdes', '9147852', '398258741', 'Clle la Amparo #96-54', '37', 'vara_12@correo.com', '2020-04-26 19:12:57.000000', '2020-04-26 19:12:57.000000', 'Ramon');
INSERT INTO `profesores` (`apellidos`, `cedula`, `celular`, `direccion`, `edad`, `email`, `fecha_modificacion`, `fecha_registro`, `nombres`) VALUES ('Meza', '85236987', '37896325', 'Mzn Cruz #20-33', '23', 'mefl_12@correo.com', '2020-04-26 19:12:57.000000', '2020-04-26 19:12:57.000000', 'Florinda');

