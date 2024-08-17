package com.pruebatecnica.moovia.consult_api.controllers;

import org.springframework.web.bind.annotation.*;
import com.pruebatecnica.moovia.consult_api.models.ConsultaModel;
import com.pruebatecnica.moovia.consult_api.repositories.ConsultaRepository;
import com.pruebatecnica.moovia.consult_api.services.TarjetaService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tarjeta")
public class TarjetaController {

    private final TarjetaService tarjetaService;
    private final ConsultaRepository consultaRepository;

    public TarjetaController(TarjetaService tarjetaService, ConsultaRepository consultaRepository) {
        this.tarjetaService = tarjetaService;
        this.consultaRepository = consultaRepository;
    }

    @GetMapping("/informacion/{numeroTarjeta}")
    public ResponseEntity<JsonNode> getInformacion(@PathVariable String numeroTarjeta, HttpServletRequest request) {
        registrarConsulta(request, numeroTarjeta);
        JsonNode response = tarjetaService.getInformacion(numeroTarjeta);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saldo/{numeroTarjeta}")
    public ResponseEntity<JsonNode> getSaldo(@PathVariable String numeroTarjeta, HttpServletRequest request) {
        registrarConsulta(request, numeroTarjeta);
        JsonNode response = tarjetaService.getSaldo(numeroTarjeta);
        return ResponseEntity.ok(response);
    }

    private void registrarConsulta(HttpServletRequest request, String numeroTarjeta) {
        ConsultaModel consulta = new ConsultaModel();
        consulta.setIpCliente(request.getRemoteAddr());
        consulta.setFechaConsulta(LocalDateTime.now());
        consulta.setTarjetaConsultada(numeroTarjeta);
        consultaRepository.save(consulta);
    }
}