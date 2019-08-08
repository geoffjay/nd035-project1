package edu.udacity.java.nano.chat.model;

import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank(message = "username cannot be empty")
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
