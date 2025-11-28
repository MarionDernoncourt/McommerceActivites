package com.mcommerceactivite.microservice_expedition.web;

import com.mcommerceactivite.microservice_expedition.dao.ExpeditionDao;
import com.mcommerceactivite.microservice_expedition.model.Expedition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ExpeditionController {

    private ExpeditionDao expeditionDao;

    public ExpeditionController(ExpeditionDao expeditionDao) {
        this.expeditionDao = expeditionDao;
    }

    @PostMapping("/expeditions")
    public ResponseEntity<Expedition> ajouterExpedition(@RequestBody Expedition expedition) {
        Expedition newExpedition = expeditionDao.save(expedition);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newExpedition.getId())
                .toUri();

        return ResponseEntity.created(location).body(newExpedition);
    }

    @GetMapping("/expeditions/{id}")
    public Optional<Expedition> afficherExpedition(@PathVariable int id) {
        Optional<Expedition> expedition = expeditionDao.findById(id);

        if(expedition.isEmpty()) {
            throw new ExpeditionNotFoundException("Cette expedition n'existe pas");
        }
        return expedition;

    }
}
