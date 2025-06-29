package com.cibertec.edu.ventas.ms_sales.repository;

import com.cibertec.edu.ventas.ms_sales.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    long countByVisto(Notificacion.VistoEstado visto);
    Optional<Notificacion> findById(Integer id);

}
