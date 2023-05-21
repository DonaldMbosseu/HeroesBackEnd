package com.example.TestAub.controller;

import com.example.TestAub.model.dto.HeroDTO;
import com.example.TestAub.service.HeroService;
import com.example.TestAub.utils.MyTimed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {
    @Autowired
    HeroService heroService;

    /**
     * Gets the list of all heroes from database
     * @return ResponseEntity<List<HeroDTO>>
     */
    @MyTimed
    @GetMapping("/all")
    ResponseEntity<List<HeroDTO>> getAllHeroes(){
        return heroService.getAllHeroes();
    }

    /**
     * Gets the list of heroes with names similar to requested name
     * @param name String
     * @return ResponseEntity<List<HeroDTO>>
     */
    @MyTimed
    @GetMapping("/get-heroes-list/{name}")
    ResponseEntity<List<HeroDTO>> getAllHeroesByName(@PathVariable("name") final String name){
        return heroService.getAllHeroesByName(name);
    }

    /**
     * Creates a new hero
     * @param heroDTO HeroDTO
     * @return ResponseEntity<HeroDTO>
     */
    @MyTimed
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroDTO> createHero(@RequestBody final HeroDTO heroDTO){
        return heroService.createHero(heroDTO);
    }

    /**
     * Gets a hero using its unique ID
     * @param id Long
     * @return ResponseEntity<HeroDTO>
     */
    @MyTimed
    @GetMapping("/get-heroes/{id}")
    public ResponseEntity<HeroDTO> getHeroById(@PathVariable("id") final Long id){
        return heroService.getHeroById(id);
    }

    /**
     * Updates a hero name
     * @param id Long
     * @param heroDTO HeroDTO
     * @return ResponseEntity<HeroDTO>
     */
    @MyTimed
    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroDTO> updateHero(@PathVariable("id") final Long id, @RequestBody HeroDTO heroDTO){
        return heroService.updateHero(id, heroDTO);
    }

    /**
     * Deletes a hero of a given name
     * @param id Long
     * @return ResponseEntity<HeroDTO>
     */
    @MyTimed
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HeroDTO> deleteCrystalById(@PathVariable("id") final Long id){
        return heroService.deleteHero(id);
    }
}
