package com.example.banking.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.banking.dto.ClientSearchCriteria;
import com.example.banking.model.Client;
import com.example.banking.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/search")
    public Page<Client> searchClients(
            @ModelAttribute ClientSearchCriteria criteria,
            Pageable pageable) {
        return clientService.searchClients(criteria, pageable);
    }
}
