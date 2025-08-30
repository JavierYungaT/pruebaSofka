package com.sofka.MS_Cuentas_Movimientos.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountNumberDTO {
    @NotBlank(message = "El numero de cuenta es requerido")
    private String accountNumber;
}
