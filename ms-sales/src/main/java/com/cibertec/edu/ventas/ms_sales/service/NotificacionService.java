package com.cibertec.edu.ventas.ms_sales.service;

import com.cibertec.edu.ventas.ms_sales.model.Notificacion;
import com.cibertec.edu.ventas.ms_sales.model.Venta;
import com.cibertec.edu.ventas.ms_sales.util.ApiResponse;

import java.util.List;

public interface NotificacionService {
    ApiResponse<Long> contarNoVistos();
    ApiResponse<List<Notificacion>> listarTodas();
    ApiResponse<String> marcarComoVista(Integer id);

}
