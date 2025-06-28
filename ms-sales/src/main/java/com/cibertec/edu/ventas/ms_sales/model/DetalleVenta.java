package com.cibertec.edu.ventas.ms_sales.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference
    private Venta venta;

    @NotNull
    private Long productoId;

    @Positive
    private Integer cantidad;

    @Positive
    private BigDecimal precioUnitario;

    @Positive
    private BigDecimal subtotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public @NotNull Long getProductoId() {
        return productoId;
    }

    public void setProductoId(@NotNull Long productoId) {
        this.productoId = productoId;
    }

    public @Positive Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(@Positive Integer cantidad) {
        this.cantidad = cantidad;
    }

    public @Positive BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(@Positive BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public @Positive BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(@Positive BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}