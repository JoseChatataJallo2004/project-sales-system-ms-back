package com.clients.ms_client.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.List;
import java.util.Map;

public class JwtUtil {

    public static List<String> extraerRolesDesdeToken(String token) {
        try {
            String[] partes = token.split("\\.");
            if (partes.length < 2) return null;

            String payload = new String(Base64.getUrlDecoder().decode(partes[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> payloadMap = mapper.readValue(payload, Map.class);

            // Asumiendo que el campo es "roles": ["JEFE"]
            return (List<String>) payloadMap.get("roles");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}