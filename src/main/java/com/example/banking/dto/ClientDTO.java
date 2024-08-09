package com.example.banking.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class ClientDTO {
	private String name;
	private LocalDate dateOfBirth;
	private Set<PhoneEmailDTO> phoneEmails;
	private double initialBalance;
	private String username;
	private String password;
}
