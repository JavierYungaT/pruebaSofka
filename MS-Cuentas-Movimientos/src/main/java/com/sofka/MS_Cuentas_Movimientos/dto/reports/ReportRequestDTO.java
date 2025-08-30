package com.sofka.MS_Cuentas_Movimientos.dto.reports;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportRequestDTO {

    private String clientDni;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
}
