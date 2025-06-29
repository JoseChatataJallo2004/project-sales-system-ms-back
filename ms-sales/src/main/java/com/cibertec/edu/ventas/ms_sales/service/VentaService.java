package com.cibertec.edu.ventas.ms_sales.service;

import com.cibertec.edu.ventas.ms_sales.model.Venta;
import com.cibertec.edu.ventas.ms_sales.util.ApiResponse;
import java.util.List;

public interface VentaService {
    ApiResponse<Venta> registrar(Venta venta);
    ApiResponse<Venta> buscarPorCodigo(String codigo);
    ApiResponse<List<Venta>> listarTodas();
    ApiResponse<String> eliminarPorCodigo(String codigo);
    //ApiResponse<Venta> actualizar(String codigo, Venta cambios);
}
