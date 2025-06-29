package com.cibertec.edu.ventas.ms_sales.repository;

import com.cibertec.edu.ventas.ms_sales.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}