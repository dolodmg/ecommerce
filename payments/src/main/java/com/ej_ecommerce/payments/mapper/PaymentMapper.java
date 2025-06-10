package com.ej_ecommerce.payments.mapper;

import com.ej_ecommerce.payments.dto.request.PaymentRequestDTO;
import com.ej_ecommerce.payments.dto.response.PaymentResponseDTO;
import com.ej_ecommerce.payments.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "idPayment", ignore = true)
    Payment toEntity(PaymentRequestDTO dto);

    PaymentResponseDTO toDto(Payment payment);
}
