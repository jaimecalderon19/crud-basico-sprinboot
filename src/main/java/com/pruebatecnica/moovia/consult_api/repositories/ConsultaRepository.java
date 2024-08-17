package com.pruebatecnica.moovia.consult_api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.moovia.consult_api.models.ConsultaModel;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {

}
