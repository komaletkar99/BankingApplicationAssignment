package com.example.banking.repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.banking.model.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByDateOfBirthGreaterThanEqual(String dateOfBirth);
    List<Client> findByPhoneEmailsPhone(String phone);
    List<Client> findByNameStartingWith(String name);
    List<Client> findByPhoneEmailsEmail(String email);
    
    @Query("SELECT c FROM Client c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:phone IS NULL OR c.phone = :phone) AND " +
            "(:email IS NULL OR c.email = :email) AND " +
            "(:dateOfBirthFrom IS NULL OR c.dateOfBirth >= :dateOfBirthFrom)")
     Page<Client> findAllByCriteria(@Param("name") String name,
                                    @Param("phone") String phone,
                                    @Param("email") String email,
                                    @Param("dateOfBirthFrom") LocalDate dateOfBirthFrom,
                                    Pageable pageable);
 }
