package com.clients.ms_client.service;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Producto;

import java.util.List;

public interface ProductoService {

    ApiResponse<Producto> registrar(Producto producto);
    ApiResponse<Producto> actualizar(Long id, Producto producto);
    ApiResponse<Void> eliminar(Long id);
    ApiResponse<List<Producto>> listar();
    ApiResponse<Producto> obtenerPorNombre(String nombre);

}
