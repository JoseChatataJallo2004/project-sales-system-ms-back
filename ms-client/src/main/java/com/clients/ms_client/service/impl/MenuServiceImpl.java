package com.clients.ms_client.service.impl;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Menu;
import com.clients.ms_client.repository.MenuRepository;
import com.clients.ms_client.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;


    @Override
    public ApiResponse<List<Menu>> obtenerMenusPorNombreRol(String nombreRol) {
        List<Menu> menus = menuRepository.findMenusByRol(nombreRol);

        if (menus == null || menus.isEmpty()) {
            return new ApiResponse<>("error", null, "No se encontraron menús para el rol: " + nombreRol);
        }

        return new ApiResponse<>("success", menus, "Menús obtenidos correctamente.");
    }
}
