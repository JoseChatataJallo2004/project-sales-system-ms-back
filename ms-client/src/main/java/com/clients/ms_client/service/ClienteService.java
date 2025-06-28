package com.clients.ms_client.service;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Cliente;

import java.util.List;

public interface ClienteService {
    ApiResponse<Cliente> registrar(Cliente cliente);
    ApiResponse<Cliente> actualizar(Long id, Cliente cliente);
    ApiResponse<Void> eliminar(Long id);
    ApiResponse<List<Cliente>> listar();
    ApiResponse<Cliente> obtenerPorDni(String dni);

}
