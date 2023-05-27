package com.kaly7dev.digitalinvoicing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long custId;
    private String name;
    private String email;
    @OneToOne(fetch = LAZY)
    private Address address;
}
