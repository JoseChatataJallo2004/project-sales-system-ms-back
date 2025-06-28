package com.clients.ms_client.service.impl;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Cliente;
import com.clients.ms_client.repository.ClienteRepository;
import com.clients.ms_client.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Override
    public ApiResponse<Cliente> registrar(Cliente cliente) {
        if (repo.existsByDni(cliente.getDni())) {
            return new ApiResponse<>("error", null, "DNI ya registrado.");
        }
        if (repo.existsByCorreo(cliente.getCorreo())) {
            return new ApiResponse<>("error", null, "Correo ya registrado.");
        }

        Cliente guardado = repo.save(cliente);
        return new ApiResponse<>("success", guardado, "Cliente registrado correctamente.");
    }

    @Override
    public ApiResponse<Cliente> actualizar(Long id, Cliente cliente) {
        Optional<Cliente> existente = repo.findById(id);
        if (!existente.isPresent()) {
            return new ApiResponse<>("error", null, "Cliente no encontrado.");
        }

        Cliente actual = existente.get();

        if (!actual.getDni().equals(cliente.getDni()) && repo.existsByDni(cliente.getDni())) {
            return new ApiResponse<>("error", null, "DNI ya registrado.");
        }

        if (!actual.getCorreo().equals(cliente.getCorreo()) && repo.existsByCorreo(cliente.getCorreo())) {
            return new ApiResponse<>("error", null, "Correo ya registrado.");
        }

        cliente.setId(id);
        Cliente actualizado = repo.save(cliente);
        return new ApiResponse<>("success", actualizado, "Cliente actualizado.");
    }

    @Override
    public ApiResponse<Void> eliminar(Long id) {
        if (!repo.existsById(id)) {
            return new ApiResponse<>("error", null, "Cliente no encontrado.");
        }

        repo.deleteById(id);
        return new ApiResponse<>("success", null, "Cliente eliminado.");
    }

    @Override
    public ApiResponse<List<Cliente>> listar() {
        List<Cliente> clientes = repo.findAll();
        return new ApiResponse<>("success", clientes, "Lista de clientes.");
    }

    @Override
    public ApiResponse<Cliente> obtenerPorDni(String dni) {
        Optional<Cliente> cliente = repo.findByDni(dni);
        if (cliente.isPresent()) {
            return new ApiResponse<>("success", cliente.get(), "Cliente encontrado.");
        } else {
            return new ApiResponse<>("error", null, "Cliente no encontrado con ese DNI.");
        }
    }
}
