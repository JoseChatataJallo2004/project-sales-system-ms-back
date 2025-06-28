package com.clients.ms_client.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    private String nombre;

    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<MenuRol> menuRoles;

    // Getters y Setters

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<MenuRol> getMenuRoles() {
        return menuRoles;
    }

    public void setMenuRoles(List<MenuRol> menuRoles) {
        this.menuRoles = menuRoles;
    }
}