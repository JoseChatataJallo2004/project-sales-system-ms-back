package com.cibertec.edu.ventas.ms_sales.controller;

import com.cibertec.edu.ventas.ms_sales.model.Notificacion;
import com.cibertec.edu.ventas.ms_sales.model.Venta;
import com.cibertec.edu.ventas.ms_sales.service.NotificacionService;
import com.cibertec.edu.ventas.ms_sales.service.VentaService;
import com.cibertec.edu.ventas.ms_sales.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public ApiResponse<Venta> registrarVenta(@RequestBody Venta venta) {
        return ventaService.registrar(venta);
    }
    @GetMapping("/{codigo}")
    public ApiResponse<Venta> buscarVentaPorCodigo(@PathVariable String codigo) {
        return ventaService.buscarPorCodigo(codigo);
    }
    @GetMapping
    public ApiResponse<List<Venta>> listarTodas() {
        return ventaService.listarTodas();
    }
    @DeleteMapping("/{codigo}")
    public ApiResponse<String> eliminar(@PathVariable String codigo) {
        return ventaService.eliminarPorCodigo(codigo);
    }
    /*@PutMapping("/{codigo}")
    public ApiResponse<Venta> actualizar(@PathVariable String codigo,
                                         @RequestBody Venta cambios) {
        return ventaService.actualizar(codigo, cambios);
    }*/

    @GetMapping("/no-vistas/count")
    public ApiResponse<Long> obtenerCantidadNoVistas() {
        return notificacionService.contarNoVistos();
    }

    @GetMapping("/notificaciones/ventas")
    public ApiResponse<List<Notificacion>> listarTodasNotificaciones() {
        return notificacionService.listarTodas();
    }

    @PutMapping("/notificaciones/{id}/vista")
    public ApiResponse<String> marcarNotificacionComoVista(@PathVariable Integer id) {
        return notificacionService.marcarComoVista(id);
    }
}