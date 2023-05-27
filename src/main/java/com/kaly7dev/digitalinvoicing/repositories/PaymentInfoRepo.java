package com.kaly7dev.digitalinvoicing.repositories;

import com.kaly7dev.digitalinvoicing.entities.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoRepo extends JpaRepository<PaymentInfo, Long> {
}
