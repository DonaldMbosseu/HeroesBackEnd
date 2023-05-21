package com.example.TestAub.service;

import com.example.TestAub.Exception.HeroCustomErrorType;
import com.example.TestAub.model.dto.HeroDTO;
import com.example.TestAub.repository.IHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService implements IHeroService{
    @Autowired
    IHeroRepository heroRepository;

    @Cacheable("heroInteractionCache")
    @Override
    public ResponseEntity<List<HeroDTO>> getAllHeroes() {
        List<HeroDTO> oHeroes = heroRepository.findAll();
        if(oHeroes.isEmpty()){
            return new ResponseEntity<List<HeroDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HeroDTO>>(oHeroes, HttpStatus.OK);
    }

    @Cacheable("heroInteractionCache")
    @Override
    public ResponseEntity<List<HeroDTO>> getAllHeroesByName(String name) {
        List<HeroDTO> oHeroes = heroRepository.getAllHeroesByName(name);
        if(oHeroes.isEmpty()){
            return new ResponseEntity<List<HeroDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HeroDTO>>(oHeroes, HttpStatus.OK);
    }

    @Cacheable("heroInteractionCache")
    @Override
    public ResponseEntity<HeroDTO> createHero(HeroDTO heroDTO) {
        Optional<HeroDTO> oHeroDTO = heroRepository.findByName(heroDTO.getName());
        if(!oHeroDTO.isEmpty()){
            return new ResponseEntity<HeroDTO>(new HeroCustomErrorType(
                    "Unable to create new hero. A hero with name "
                            + heroDTO.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        heroRepository.save(heroDTO);
        return new ResponseEntity<HeroDTO>(heroDTO, HttpStatus.CREATED);
    }

    @Cacheable("heroInteractionCache")
    @Override
    public ResponseEntity<HeroDTO> getHeroById(Long id) {
        Optional<HeroDTO> oHeroDTO = heroRepository.findById(id);

        if(oHeroDTO.isEmpty()){
            return new ResponseEntity<HeroDTO>(new HeroCustomErrorType("Hero with id " +
                    + id + " not found"), HttpStatus.NOT_FOUND);
        }

        HeroDTO heroDTO = oHeroDTO.get();
        return new ResponseEntity<HeroDTO>(heroDTO, HttpStatus.OK);
    }

    @Cacheable("heroInteractionCache")
    @Override
    public ResponseEntity<HeroDTO> updateHero(Long id, HeroDTO heroDTO) {
        Optional<HeroDTO> oHeroDTO = heroRepository.findById(id);
        if (oHeroDTO.isEmpty()) {
            return new ResponseEntity<HeroDTO>(
                    new HeroCustomErrorType("Unable to update. Hero with id "
                            + id + " not found."), HttpStatus.NOT_FOUND);
        }

        HeroDTO heroDTO1 = oHeroDTO.get();
        heroDTO1.setName(heroDTO.getName());
        heroRepository.save(heroDTO1);
        return new ResponseEntity<HeroDTO>(heroDTO1, HttpStatus.OK);
    }

    @Cacheable("heroInteractionCache")
    @Override
    public ResponseEntity<HeroDTO> deleteHero(Long id) {
        Optional<HeroDTO> oHeroDTO = heroRepository.findById(id);
        if (oHeroDTO.isEmpty()) {
            return new ResponseEntity<HeroDTO>(
                    new HeroCustomErrorType("Unable to delete. Hero with id "
                            + id + " not found."), HttpStatus.NOT_FOUND);
        }
        heroRepository.deleteById(id);
        return new ResponseEntity<HeroDTO>(
                new HeroCustomErrorType("Deleted Player with id "
                        + id + "."), HttpStatus.NO_CONTENT);
    }
}
