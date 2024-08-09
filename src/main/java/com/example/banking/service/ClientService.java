package com.example.banking.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.banking.dto.ClientDTO;
import com.example.banking.dto.ClientSearchCriteria;
import com.example.banking.dto.PhoneEmailDTO;
import com.example.banking.exception.ResourceNotFoundException;
import com.example.banking.model.Account;
import com.example.banking.model.Client;
import com.example.banking.model.PhoneEmail;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.ClientRepository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setDateOfBirth(clientDTO.getDateOfBirth());

        // Create account and set initial balance
        Account account = new Account();
        account.setInitialBalance(new BigDecimal(clientDTO.getInitialBalance()));
        account.setCurrentBalance(new BigDecimal(clientDTO.getInitialBalance()));
        client.setAccount(account);

        // Add phone numbers and emails
        for (PhoneEmailDTO peDTO : clientDTO.getPhoneEmails()) {
            PhoneEmail pe = new PhoneEmail();
            pe.setPhone(peDTO.getPhone());
            pe.setEmail(peDTO.getEmail());
            client.addPhoneEmail(pe);
        }

        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    public List<Client> searchClients(ClientSearchCriteria criteria) {
        // Implement search logic using criteria
        return clientRepository.findAll(); // Modify this to use actual criteria
    }

    @Transactional
    public void updateBalance(Long accountId, BigDecimal interest) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.increaseBalance(interest);
        accountRepository.save(account);
    }
    
   

    public Page<Client> searchClients(ClientSearchCriteria criteria, Pageable pageable) {
        return clientRepository.findAllByCriteria(
            criteria.getName(),
            criteria.getPhone(),
            criteria.getEmail(),
            criteria.getDateOfBirthFrom(),
            pageable
        );
    }

}

