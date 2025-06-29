package com.cibertec.edu.ventas.ms_sales.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nroventa", length = 50)
    private String nroventa;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "visto", nullable = false)
    @Enumerated(EnumType.STRING)
    private VistoEstado visto = VistoEstado.No;

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNroventa() {
        return nroventa;
    }

    public void setNroventa(String nroventa) {
        this.nroventa = nroventa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public VistoEstado getVisto() {
        return visto;
    }

    public void setVisto(VistoEstado visto) {
        this.visto = visto;
    }

    // Enum para el campo visto
    public enum VistoEstado {
        Si, No
    }
}