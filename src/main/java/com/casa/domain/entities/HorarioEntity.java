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
@Table(name = "horarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_dia", nullable = false, referencedColumnName = "id")
	private DiaEntity dia;
	
	@ManyToOne
    @JoinColumn(name = "id_materia", nullable = false, referencedColumnName = "id")
	private MateriaEntity materia;
	
	@ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false, referencedColumnName = "id")
	private ProfesorEntity profesor;

	@Column(nullable = false, name = "id_curso")
	private Long idCurso;

	@Column(nullable = false, name = "horas_dictar", length = 1)
	private Integer horasDictar;
	
	@Column(nullable = false, name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

}
