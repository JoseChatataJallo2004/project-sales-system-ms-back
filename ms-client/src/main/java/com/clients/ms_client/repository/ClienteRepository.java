package com.clients.ms_client.repository;

import com.clients.ms_client.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    boolean existsByDni(String dni);
    boolean existsByCorreo(String correo);
    Optional<Cliente> findByDni(String dni);

}
