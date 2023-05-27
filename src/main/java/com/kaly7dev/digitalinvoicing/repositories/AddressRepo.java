package com.kaly7dev.digitalinvoicing.repositories;

import com.kaly7dev.digitalinvoicing.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
