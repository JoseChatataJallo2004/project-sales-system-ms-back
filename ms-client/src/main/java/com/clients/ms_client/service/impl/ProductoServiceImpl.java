package com.clients.ms_client.service.impl;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Producto;
import com.clients.ms_client.repository.ProductoRepository;
import com.clients.ms_client.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository repo;

    @Override
    public ApiResponse<Producto> registrar(Producto producto) {
        if (repo.existsByNombre(producto.getNombre())) {
            return new ApiResponse<>("error", null, "El nombre del producto ya existe.");
        }

        Producto guardado = repo.save(producto);
        return new ApiResponse<>("success", guardado, "Producto registrado correctamente.");
    }

    @Override
    public ApiResponse<Producto> actualizar(Long id, Producto producto) {
        Optional<Producto> existente = repo.findById(id);
        if (!existente.isPresent()) {
            return new ApiResponse<>("error", null, "Producto no encontrado.");
        }

        Producto actual = existente.get();

        if (!actual.getNombre().equalsIgnoreCase(producto.getNombre()) && repo.existsByNombre(producto.getNombre())) {
            return new ApiResponse<>("error", null, "El nombre del producto ya existe.");
        }

        producto.setId(id);
        Producto actualizado = repo.save(producto);
        return new ApiResponse<>("success", actualizado, "Producto actualizado.");
    }

    @Override
    public ApiResponse<Void> eliminar(Long id) {
        if (!repo.existsById(id)) {
            return new ApiResponse<>("error", null, "Producto no encontrado.");
        }

        repo.deleteById(id);
        return new ApiResponse<>("success", null, "Producto eliminado.");
    }

    @Override
    public ApiResponse<List<Producto>> listar() {
        List<Producto> productos = repo.findAll();
        return new ApiResponse<>("success", productos, "Lista de productos.");
    }

    @Override
    public ApiResponse<Producto> obtenerPorNombre(String nombre) {
        Optional<Producto> producto = repo.findByNombre(nombre);
        if (producto.isPresent()) {
            return new ApiResponse<>("success", producto.get(), "Producto encontrado.");
        } else {
            return new ApiResponse<>("error", null, "Producto no encontrado con ese nombre.");
        }
    }
}
