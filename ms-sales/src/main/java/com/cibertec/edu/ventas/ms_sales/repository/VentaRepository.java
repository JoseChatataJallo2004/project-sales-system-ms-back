package com.cibertec.edu.ventas.ms_sales.repository;

import com.cibertec.edu.ventas.ms_sales.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}