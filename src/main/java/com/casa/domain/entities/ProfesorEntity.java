package com.casa.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profesores")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
	private Long id;
	
	@Column(nullable = false, length = 80, unique = true)
	private String cedula;
	
	@Column(nullable = false, length = 50)
	private String nombres;
	
	@Column(nullable = false, length = 50)
	private String apellidos;
	
	@Column(nullable = false, length = 3)
	private Integer edad;
	
	@Column(nullable = false, length = 13)
	private String celular;
	
	@Column(nullable = false, length = 150)
	private String direccion;
	
	@Column(nullable = false, length = 150)
	private String email;
	
	@Column(nullable = false, name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

}
