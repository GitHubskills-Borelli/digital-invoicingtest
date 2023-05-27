package com.kaly7dev.digitalinvoicing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
@Builder
public class Invoice {
    @Id
    private String invId;
    private String invNumber;
    @ManyToOne(fetch = LAZY)
    //@JoinColumn(name = "custId",referencedColumnName = "custId")
    private Customer customer;
    @OneToMany(fetch = LAZY)
    private List<InvoiceItem> items;
    @ManyToOne(fetch = LAZY)
    //@JoinColumn(name="addId",referencedColumnName = "addId")
    private Address billingAddress;
    @ManyToOne(fetch = LAZY)
    //@JoinColumn(name="id", referencedColumnName = "id")
    private PaymentInfo paymentInfo;
    private Double totalAmount;
}
