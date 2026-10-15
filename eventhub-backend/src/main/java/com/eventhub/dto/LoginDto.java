package com.eventhub.dto;

public class LoginDto {

    private String email;
    private String password;

    // Default Constructor
    public LoginDto() {
    }

    // Parameterized Constructor
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter
    public String getEmail() {
        return email;
    }

    // Setter
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter
    public String getPassword() {
        return password;
    }

    // Setter
    public void setPassword(String password) {
        this.password = password;
    }
}