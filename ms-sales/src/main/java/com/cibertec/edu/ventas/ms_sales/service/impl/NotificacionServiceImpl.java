package com.cibertec.edu.ventas.ms_sales.service.impl;

import com.cibertec.edu.ventas.ms_sales.model.Notificacion;
import com.cibertec.edu.ventas.ms_sales.repository.NotificacionRepository;
import com.cibertec.edu.ventas.ms_sales.service.NotificacionService;
import com.cibertec.edu.ventas.ms_sales.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;


    @Override
    public ApiResponse<Long> contarNoVistos() {
        long count = notificacionRepository.countByVisto(Notificacion.VistoEstado.No);
        return new ApiResponse<>("success", count,"Total de notificaciones no vistas");
    }

    @Override
    public ApiResponse<List<Notificacion>> listarTodas() {
        //return null;
        return new ApiResponse<>("success", notificacionRepository.findAll(), "Listado de notificaciones.");

    }

    @Override
    public ApiResponse<String> marcarComoVista(Integer id) {
        Optional<Notificacion> optional = notificacionRepository.findById(id);

        if (optional.isPresent()) {
            Notificacion notif = optional.get();
            notif.setVisto(Notificacion.VistoEstado.Si);
            notificacionRepository.save(notif);

            return new ApiResponse<>("success", "Notificación marcada como vista", null);
        } else {
            return new ApiResponse<>("error", "Notificación no encontrada", null);
        }
    }
}
