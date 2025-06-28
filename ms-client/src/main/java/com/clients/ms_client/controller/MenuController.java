package com.clients.ms_client.controller;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.Utils.JwtUtil;
import com.clients.ms_client.model.Menu;
import com.clients.ms_client.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients/menu")
//@CrossOrigin(origins = "*") // Puedes ajustar el CORS según tu necesidad
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/por-rol")
    public ResponseEntity<ApiResponse<List<Menu>>> obtenerMenusPorRol(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("error", null, "Token no proporcionado o inválido"));
        }

        String token = authHeader.substring(7);
        List<String> roles = JwtUtil.extraerRolesDesdeToken(token);

        if (roles == null || roles.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("error", null, "No se encontraron roles en el token"));
        }

        String rol = roles.get(0); // si tienes más, puedes iterar

        ApiResponse<List<Menu>> response = menuService.obtenerMenusPorNombreRol(rol);
        return ResponseEntity.ok(response);
    }

}