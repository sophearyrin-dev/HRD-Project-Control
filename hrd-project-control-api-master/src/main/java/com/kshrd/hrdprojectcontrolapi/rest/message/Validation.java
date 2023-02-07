package com.kshrd.hrdprojectcontrolapi.rest.message;

import org.springframework.stereotype.Component;

@Component
public class Validation {

    private String name;
    public boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
    public boolean checkInputPassword(String input)
    {
        return (input==null || input.trim().length() == 0 || input.length()<8);
    }
}
