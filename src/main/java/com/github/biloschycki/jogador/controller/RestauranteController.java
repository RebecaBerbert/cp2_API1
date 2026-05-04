package com.github.biloschycki.jogador.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.biloschycki.jogador.model.Restaurante;
import com.github.biloschycki.jogador.repository.RestauranteRepository;


@RestController
@RequestMapping("api/${api.version}/pedidos")
public class RestauranteController {


    @Autowired
    private RestauranteRepository repository;

    @PostMapping
    public ResponseEntity<Restaurante> create(@RequestBody Restaurante restaurante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(restaurante));
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

     @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Optional<Restaurante> optRestaurante = repository.findById(id);
        if(optRestaurante.isPresent()){
            restaurante.setId(id);
            Restaurante restauranteAlterado=repository.save(restaurante);
            return ResponseEntity.ok(restauranteAlterado);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }
}


