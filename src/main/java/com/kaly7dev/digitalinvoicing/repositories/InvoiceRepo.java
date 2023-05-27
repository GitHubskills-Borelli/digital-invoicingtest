package com.kaly7dev.digitalinvoicing.repositories;

import com.kaly7dev.digitalinvoicing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, String> {
}
