package com.example.banking.dto;

import java.time.LocalDate;

public class ClientSearchCriteria {

    private String name;
    private String phone;
    private String email;
    private LocalDate dateOfBirthFrom;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirthFrom() {
        return dateOfBirthFrom;
    }

    public void setDateOfBirthFrom(LocalDate dateOfBirthFrom) {
        this.dateOfBirthFrom = dateOfBirthFrom;
    }
}
