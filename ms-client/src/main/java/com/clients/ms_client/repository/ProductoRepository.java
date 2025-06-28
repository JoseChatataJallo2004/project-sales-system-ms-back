package com.clients.ms_client.repository;

import com.clients.ms_client.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
    Optional<Producto> findByNombre(String nombre);

}
