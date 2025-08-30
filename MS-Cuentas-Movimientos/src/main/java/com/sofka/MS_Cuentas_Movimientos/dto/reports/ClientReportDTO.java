package com.sofka.MS_Cuentas_Movimientos.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientReportDTO {
    private String clientDni;
    private String clientName;
    private List<AccountReportDTO> accounts;
}
