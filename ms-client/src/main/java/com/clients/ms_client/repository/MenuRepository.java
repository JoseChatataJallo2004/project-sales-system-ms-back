package com.clients.ms_client.repository;

import com.clients.ms_client.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query(value = "{CALL sp_getMenusByRol(:rolNombre)}", nativeQuery = true)
   List<Menu> findMenusByRol(@Param("rolNombre") String rolNombre);

   /* @Query("SELECT m FROM Menu m " +
            "JOIN MenuRol mr ON m.idMenu = mr.menu.idMenu " +
            "JOIN Rol r ON mr.rol.idRol = r.idRol " +
            "WHERE r.nombre = :nombreRol")
    List<Menu> findMenusByNombreRol(@Param("nombreRol") String nombreRol);*/
}