package com.casa.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "semanas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SemanaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_mes", nullable = false, referencedColumnName = "id")
	private MesEntity mes;
	
//	Numero del mes en el año
	@Column(nullable = false, length = 3, name = "numero_semana_mes")
	private Integer numeroSemanaMes;
	
	@Column(nullable = false, length = 15)
	private String nombre;
	
	@Column(nullable = false, name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

}
