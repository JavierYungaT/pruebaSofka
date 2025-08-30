package com.sofka.MS_Cuentas_Movimientos.dto;

import lombok.Data;

@Data
public class ClientCreatedEvent {
    private Integer clientId;
    private ClientEventDTO client;
}
