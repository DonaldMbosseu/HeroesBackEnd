package com.example.TestAub.repository;

import com.example.TestAub.model.dto.HeroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IHeroRepository extends JpaRepository<HeroDTO, Long> {
    @Query("SELECT u FROM HeroDTO u WHERE LOWER(u.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<HeroDTO> getAllHeroesByName(@Param("name") String name);
    @Query("SELECT u FROM HeroDTO u WHERE u.name = ?1")
    Optional<HeroDTO> findByName(String name);
}
