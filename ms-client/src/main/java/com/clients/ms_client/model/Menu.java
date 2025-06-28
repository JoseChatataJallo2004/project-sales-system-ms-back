package com.clients.ms_client.model;

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMenu;

    private String nombre;

    private String icono;

    private String url;

  /*  @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuRol> menuRoles;
*/
    // Getters y Setters

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   /* public List<MenuRol> getMenuRoles() {
        return menuRoles;
    }

    public void setMenuRoles(List<MenuRol> menuRoles) {
        this.menuRoles = menuRoles;
    }*/
}