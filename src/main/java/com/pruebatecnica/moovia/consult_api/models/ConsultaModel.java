package com.pruebatecnica.moovia.consult_api.models;

import lombok.Data;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "consultas")
public class ConsultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ip_cliente")
    private String ipCliente;
    
    @Column(name = "fecha_consulta")
    private LocalDateTime fechaConsulta;
    
    @Column(name = "tarjeta_consultada")
    private String tarjetaConsultada;
}
