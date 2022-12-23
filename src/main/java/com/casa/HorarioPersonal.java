package com.casa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HorarioPersonal implements CommandLineRunner{
	
	private final Logger log = LoggerFactory.getLogger(HorarioPersonal.class);

	public static void main(String[] args) {
		SpringApplication.run(HorarioPersonal.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("HorarioPersonal - run() -> Iniciando servicios de Horario");
	}

}
