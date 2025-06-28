package com.clients.ms_client.controller;

import com.clients.ms_client.Utils.ApiResponse;
import com.clients.ms_client.model.Producto;
import com.clients.ms_client.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> registrar(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.registrar(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.eliminar(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/buscar-por-nombre/{nombre}")
    public ResponseEntity<ApiResponse<Producto>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.obtenerPorNombre(nombre));
    }
}
