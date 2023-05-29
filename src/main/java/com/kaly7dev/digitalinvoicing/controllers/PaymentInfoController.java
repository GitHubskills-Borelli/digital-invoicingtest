package com.kaly7dev.digitalinvoicing.controllers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.PaymentInfoDto;
import com.kaly7dev.digitalinvoicing.services.PaymentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paymentinfo")
@RequiredArgsConstructor
public class PaymentInfoController {
    private final PaymentInfoService paymentInfoService;
    @PostMapping("/create")
    public ResponseEntity<String> createPaymentInfo(@RequestBody PaymentInfoDto paymentInfoDto){
        paymentInfoService.createPaymentInfo(paymentInfoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<PaymentInfoDto>> paymentInfoList(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentInfoService.paymentInfoList());
    }
}
