package com.sofka.MS_Cuentas_Movimientos.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountReportDTO {

    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private List<MovementDetailDTO> movements;
}
