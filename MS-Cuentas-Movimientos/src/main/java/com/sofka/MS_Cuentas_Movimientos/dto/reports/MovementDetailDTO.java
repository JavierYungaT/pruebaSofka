package com.sofka.MS_Cuentas_Movimientos.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MovementDetailDTO {
    private String movementDate;
    private String movementType;
    private BigDecimal movementAmount;
    private BigDecimal movementBalance;
}
