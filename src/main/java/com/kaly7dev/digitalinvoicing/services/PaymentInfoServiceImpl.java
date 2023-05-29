package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.PaymentInfoDto;
import com.kaly7dev.digitalinvoicing.core_api.mappers.PaymentInfoMapper;
import com.kaly7dev.digitalinvoicing.repositories.PaymentInfoRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {
    private final PaymentInfoRepo paymentInfoRepo;
    private final PaymentInfoMapper paymentInfoMapper;
    @Override
    @Transactional
    public void createPaymentInfo(PaymentInfoDto paymentInfoDto) {
        paymentInfoRepo.save(paymentInfoMapper.mapToEntity(paymentInfoDto));
        log.info(" PaymentInfo created Successfully ! ");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentInfoDto> paymentInfoList() {

        List<PaymentInfoDto> paymentInfoDtoList = paymentInfoRepo.findAll()
                .stream()
                .map(
                        paymentInfoMapper::mapToDto
                ).collect(Collectors.toList());
        log.info(" PaymentInfo list successfuly displayed ! ");
        return paymentInfoDtoList;
    }
}
