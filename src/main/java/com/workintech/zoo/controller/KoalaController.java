package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> findAll() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala findById(@PathVariable("id") long id) {
        if (id <= 0) {
            throw new ZooException("Id canNOT be less than or equal to zero", HttpStatus.BAD_REQUEST);
        }

        if (!koalas.containsKey(id)) {
            throw new ZooException("Not a valid id", HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);

        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable("id") long id, @RequestBody Koala koala) {
        koalas.put(id, koala);

        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable("id") long id) {
        Koala koala = koalas.get(id);
        koalas.remove(id);

        return koala;
    }
}
