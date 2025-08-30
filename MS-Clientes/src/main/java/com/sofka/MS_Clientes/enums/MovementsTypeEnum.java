package com.sofka.MS_Clientes.enums;

public enum MovementsTypeEnum {

    DEPOSIT("Deposito"),
    WITHDRAWAL("Retiro");

    private final String message;

    MovementsTypeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
