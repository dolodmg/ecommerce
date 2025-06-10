package com.ej_ecommerce.payments.dto.response;

import com.ej_ecommerce.payments.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long idPayment;
    private Status status;
    private Long idUser;
    private Long idOrder;
    private LocalDateTime dateTime;
    private Double total;
    private boolean active;
}
