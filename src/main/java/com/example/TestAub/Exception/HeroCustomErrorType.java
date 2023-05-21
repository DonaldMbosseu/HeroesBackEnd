package com.example.TestAub.Exception;

import com.example.TestAub.model.dto.HeroDTO;

public class HeroCustomErrorType extends HeroDTO {
    private String errorMessage;

    public HeroCustomErrorType(final String errorMessage){
        super("");
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
