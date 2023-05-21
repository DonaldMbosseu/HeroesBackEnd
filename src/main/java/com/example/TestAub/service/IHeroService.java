package com.example.TestAub.service;

import com.example.TestAub.model.dto.HeroDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHeroService {

    ResponseEntity<List<HeroDTO>> getAllHeroes();

    ResponseEntity<List<HeroDTO>> getAllHeroesByName(String name);

    ResponseEntity<HeroDTO> createHero(HeroDTO heroDTO);

    ResponseEntity<HeroDTO> getHeroById(Long id);

    ResponseEntity<HeroDTO> updateHero(Long id, HeroDTO heroDTO);

    ResponseEntity<HeroDTO> deleteHero(Long id);
}
