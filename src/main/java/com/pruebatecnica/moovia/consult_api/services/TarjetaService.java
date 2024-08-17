package com.pruebatecnica.moovia.consult_api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class TarjetaService {

    @Value("${api.base-url}")
    private String apiBaseUrl;

    @Value("${api.bearer-token}")
    private String bearerToken;

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public TarjetaService(ObjectMapper objectMapper) {
        this.restClient = RestClient.create();
        this.objectMapper = objectMapper;
    }

    public JsonNode getInformacion(String numeroTarjeta) {
        try {
            String response = restClient.get()
                    .uri(apiBaseUrl + "/card/getInformation/" + numeroTarjeta)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                    .retrieve()
                    .body(String.class);
            return objectMapper.readTree(response);
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            return objectMapper.createObjectNode().put("error", e.getMessage());
        }
    }

    public JsonNode getSaldo(String numeroTarjeta) {
        try {
            String response = restClient.get()
                    .uri(apiBaseUrl + "/card/getBalance/" + numeroTarjeta)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                    .retrieve()
                    .body(String.class);
            return objectMapper.readTree(response);
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            return objectMapper.createObjectNode().put("error", e.getMessage());
        }
    }
}