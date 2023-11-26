package com.turkcell.customerservice.repository;

import com.turkcell.customerservice.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.balance = :balance WHERE c.id = :id")
    void updateBalance(UUID id, double balance);
}
