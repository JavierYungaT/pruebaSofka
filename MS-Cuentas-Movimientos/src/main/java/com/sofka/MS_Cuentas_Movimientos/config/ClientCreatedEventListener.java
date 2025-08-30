package com.sofka.MS_Cuentas_Movimientos.config;

import com.sofka.MS_Cuentas_Movimientos.dto.account.SaveAccountDTO;
import com.sofka.MS_Cuentas_Movimientos.events.ClientCreatedEvent;
import com.sofka.MS_Cuentas_Movimientos.events.Event;
import com.sofka.MS_Cuentas_Movimientos.exceptions.CustomValidationException;
import com.sofka.MS_Cuentas_Movimientos.service.AccountService;
import com.sofka.MS_Cuentas_Movimientos.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientCreatedEventListener {

    private final AccountService accountService;

    @KafkaListener(topics = "customers",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "customers")
    public void consumer(Event<?> event) throws CustomValidationException {
        if (event.getClass().isAssignableFrom(ClientCreatedEvent.class)) {

            ClientCreatedEvent clientCreatedEvent = (ClientCreatedEvent) event;
            SaveAccountDTO accountDTO = new SaveAccountDTO(
                    Utils.generateAccountNumber(clientCreatedEvent.getData().getIdentification()),
                    clientCreatedEvent.getData().getAccountType(),
                    clientCreatedEvent.getData().getInitialBalance(),
                    Boolean.TRUE,
                    clientCreatedEvent.getData().getIdentification(),
                    clientCreatedEvent.getData().getName()
            );
            accountService.saveAccount(accountDTO);
        }
    }
}
