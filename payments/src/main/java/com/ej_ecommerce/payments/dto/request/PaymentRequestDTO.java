package com.ej_ecommerce.payments.dto.request;

import com.ej_ecommerce.payments.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private Long idUser;
    private Long idOrder;
}
