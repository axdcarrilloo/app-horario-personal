package com.casa.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "horas_dia_curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorasDiaCursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_dia", nullable = false, referencedColumnName = "id")
    private DiaEntity dia;

    @Column(name = "id_curso", nullable = false, length = 10)
    private Long idCurso;

    @Column(name = "cantidad_horas", nullable = false, length = 1)
    private Integer cantidadHoras;

    @Column(nullable = false, name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(nullable = false, name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

}
