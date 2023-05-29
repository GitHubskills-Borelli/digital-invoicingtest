package com.kaly7dev.digitalinvoicing.repositories;

import com.kaly7dev.digitalinvoicing.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    //Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByNameContaining(String name, Pageable pageable);
}
