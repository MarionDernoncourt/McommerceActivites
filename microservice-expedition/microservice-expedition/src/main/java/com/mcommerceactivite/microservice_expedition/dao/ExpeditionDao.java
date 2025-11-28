package com.mcommerceactivite.microservice_expedition.dao;

import com.mcommerceactivite.microservice_expedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {

    List<Expedition> id(int id);
}
