package com.sofka.MS_Clientes.events;

import com.sofka.MS_Clientes.dto.client.ClientAccountEventDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEvent extends Event<ClientAccountEventDTO> {

}
