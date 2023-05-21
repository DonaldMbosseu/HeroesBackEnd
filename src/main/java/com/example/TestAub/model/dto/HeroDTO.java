package com.example.TestAub.model.dto;

import jakarta.persistence.*;

@Table(name = "hero")
@Entity
public class HeroDTO {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public HeroDTO(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  HeroDTO(){

    }

}
