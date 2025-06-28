package com.clients.ms_client.service;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Menu;

import java.util.List;

public interface MenuService {
    ApiResponse<List<Menu>> obtenerMenusPorNombreRol(String nombreRol);

}
