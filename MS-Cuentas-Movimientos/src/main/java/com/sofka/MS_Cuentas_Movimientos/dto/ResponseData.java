package com.sofka.MS_Cuentas_Movimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {
    private String message;
    private String code;
    private String status;
}
