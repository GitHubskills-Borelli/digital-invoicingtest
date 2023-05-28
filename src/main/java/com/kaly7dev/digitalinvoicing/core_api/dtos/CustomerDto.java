package com.kaly7dev.digitalinvoicing.core_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Long custId;
    private String name;
    private String email;
    private String phone;
    private AddressDto addressDto;
}
