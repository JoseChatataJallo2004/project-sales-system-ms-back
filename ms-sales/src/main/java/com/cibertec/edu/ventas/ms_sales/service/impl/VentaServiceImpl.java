package com.cibertec.edu.ventas.ms_sales.service.impl;

import com.cibertec.edu.ventas.ms_sales.model.DetalleVenta;
import com.cibertec.edu.ventas.ms_sales.model.Producto;
import com.cibertec.edu.ventas.ms_sales.model.Venta;
import com.cibertec.edu.ventas.ms_sales.repository.ProductoRepository;
import com.cibertec.edu.ventas.ms_sales.repository.VentaRepository;
import com.cibertec.edu.ventas.ms_sales.service.VentaService;
import com.cibertec.edu.ventas.ms_sales.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public ApiResponse<Venta> registrar(Venta venta) {
        // Generar código correlativo tipo "P00001"
        long count = ventaRepo.count() + 1;
        String codigo = String.format("P%05d", count);

        if (ventaRepo.existsByCodigo(codigo)) {
            return new ApiResponse<>("error", null, "El código ya existe.");
        }

        venta.setCodigo(codigo);

        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoRepo.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto con ID " + detalle.getProducto().getId() + " no encontrado"));

            if (producto.getStock() < detalle.getCantidad()) {
                return new ApiResponse<>("error", null,
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepo.save(producto);

            // 🔧 Corrección aquí:
            detalle.setProducto(producto);
            detalle.setVenta(venta);

            BigDecimal subtotal = BigDecimal.valueOf(producto.getPrecio())
                    .multiply(BigDecimal.valueOf(detalle.getCantidad()));
            detalle.setSubtotal(subtotal);
            total = total.add(subtotal);
        }


        venta.setMontoTotal(total);

        Venta guardado = ventaRepo.save(venta);
        return new ApiResponse<>("success", guardado, "Venta registrada correctamente.");
    }

    @Override
    public ApiResponse<Venta> buscarPorCodigo(String codigo) {
        Optional<Venta> venta = ventaRepo.findByCodigo(codigo);
        if (venta.isEmpty()) {
            return new ApiResponse<>("error", null, "No se encontró la venta con ese código.");
        }
        return new ApiResponse<>("success", venta.get(), "Venta encontrada.");
    }

    @Override
    public ApiResponse<List<Venta>> listarTodas() {
        return new ApiResponse<>("success", ventaRepo.findAll(), "Listado de ventas registradas correctamente.");
    }


    @Override
    public ApiResponse<String> eliminarPorCodigo(String codigo) {
        Optional<Venta> venta = ventaRepo.findByCodigo(codigo);
        if (venta.isEmpty()) {
            return new ApiResponse<>("error", null, "No se encontró la venta.");
        }
        ventaRepo.delete(venta.get());
        return new ApiResponse<>("success", codigo, "Venta eliminada correctamente.");
    }
    /*@Override
    public ApiResponse<Venta> actualizar(String codigo, Venta cambios) {
        Optional<Venta> optVenta = ventaRepo.findByCodigo(codigo);
        if (optVenta.isEmpty()) {
            return new ApiResponse<>("error", null, "Venta no encontrada.");
        }

        Venta venta = optVenta.get();

        // ⇢ 1. Fecha (si llega)
        if (cambios.getFecha() != null) {
            venta.setFecha(cambios.getFecha());
        }

        // ⇢ 2. Detalles (si llegan)
        if (cambios.getDetalles() != null && !cambios.getDetalles().isEmpty()) {
            venta.getDetalles().clear();                       // reseteamos detalles
            BigDecimal nuevoTotal = BigDecimal.ZERO;

            for (DetalleVenta det : cambios.getDetalles()) {
                det.setVenta(venta);                           // back-ref
                BigDecimal sub = det.getProducto().getPrecio()
                        .multiply(BigDecimal.valueOf(det.getCantidad()));
                det.setSubtotal(sub);
                nuevoTotal = nuevoTotal.add(sub);
                venta.getDetalles().add(det);
            }
            venta.setMontoTotal(nuevoTotal);                   // recalculado
        }

        // ⇢ 3. (Opcional) estado, observaciones, etc. (más campos a gusto)

        Venta guardado = ventaRepo.save(venta);
        return new ApiResponse<>("success", guardado, "Venta actualizada.");
    }*/
}