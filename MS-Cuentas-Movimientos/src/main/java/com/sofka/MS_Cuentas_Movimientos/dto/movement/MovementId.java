package com.sofka.MS_Cuentas_Movimientos.dto.movement;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovementId {
    @NotNull(message = "El id del movimiento es requerido")
    private Integer movementId;
}
