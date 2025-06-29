package com.cibertec.edu.ventas.ms_sales.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 6, nullable = false)
    private String codigo;   // ej. P00001

    @NotNull
    private LocalDateTime fecha = LocalDateTime.now();

    @PositiveOrZero
    private BigDecimal montoTotal;

    @OneToMany(mappedBy = "venta",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetalleVenta> detalles = new ArrayList<>();

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public @NotNull LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public @PositiveOrZero BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(@PositiveOrZero BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}