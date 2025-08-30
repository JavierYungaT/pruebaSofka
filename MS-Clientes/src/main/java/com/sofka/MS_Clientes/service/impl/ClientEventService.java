package com.sofka.MS_Clientes.service.impl;

import com.sofka.MS_Clientes.dto.client.ClientAccountEventDTO;
import com.sofka.MS_Clientes.events.ClientCreatedEvent;
import com.sofka.MS_Clientes.events.Event;
import com.sofka.MS_Clientes.events.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientEventService {


    private final KafkaTemplate<String, Event<?>> producer;



    public void publish(ClientAccountEventDTO clientAccountEventDTO) {
        ClientCreatedEvent createdEvent = new ClientCreatedEvent();
        createdEvent.setData(clientAccountEventDTO);
        createdEvent.setId(UUID.randomUUID().toString());
        createdEvent.setType(EventType.CREATED);
        createdEvent.setDate(new Date());
        producer.send("customers", createdEvent);
    }


}
