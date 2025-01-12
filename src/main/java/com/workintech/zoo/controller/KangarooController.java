package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> findAll() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable("id") long id) {
        if (id <= 0) {
            throw new ZooException("Id canNOT be less than or equal to zero", HttpStatus.BAD_REQUEST);
        }

        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Not a valid id", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);

        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable("id") long id, @RequestBody Kangaroo kangaroo) {
        kangaroos.put(id, kangaroo);

        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable("id") long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);

        return kangaroo;
    }

}
