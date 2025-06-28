package com.clients.ms_client.controller;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Cliente;
import com.clients.ms_client.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients/clientes")
public class ClientController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ApiResponse<Cliente>> registrar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.registrar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.eliminar(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Cliente>>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/buscar-por-dni/{dni}")
    public ResponseEntity<ApiResponse<Cliente>> buscarPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(clienteService.obtenerPorDni(dni));
    }
}
