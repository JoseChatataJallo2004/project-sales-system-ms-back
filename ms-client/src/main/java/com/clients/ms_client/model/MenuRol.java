package com.clients.ms_client.model;

import jakarta.persistence.*;
@Entity
@Table(name = "MenuRol")
public class MenuRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMenuRol;

    @ManyToOne
    @JoinColumn(name = "idMenu")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    // Getters y Setters

    public Integer getIdMenuRol() {
        return idMenuRol;
    }

    public void setIdMenuRol(Integer idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}