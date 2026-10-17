package com.mycompany.protyectopersistencia001.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_ruta")
@Getter @Setter @NoArgsConstructor
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ru_id", nullable = false)
    private Integer id;

    @Column(name = "ru_origen", nullable = false)
    private String origen;

    @Column(name = "ru_destino", nullable = false)
    private String destino;

    @Column(name = "ru_distancia")
    private Double distancia;
}
