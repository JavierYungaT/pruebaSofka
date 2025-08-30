package com.sofka.MS_Clientes.service;


import com.sofka.MS_Clientes.dto.ResponseData;
import com.sofka.MS_Clientes.dto.client.ClientDTO;
import com.sofka.MS_Clientes.dto.client.UserDniDTO;
import com.sofka.MS_Clientes.dto.client.UserIdDTO;
import com.sofka.MS_Clientes.exceptions.CustomValidationException;

import java.util.List;
import java.util.Map;

public interface ClientService {

    List<ClientDTO> getAllClients();
    ClientDTO getUserById(UserIdDTO userIdDTO) throws CustomValidationException;
    ResponseData saveClient(ClientDTO clientDTO) throws CustomValidationException;
    ResponseData updateClient(Integer userId, Map<String, Object> fields) throws CustomValidationException;
    ResponseData deleteClient(UserDniDTO userDniDTO) throws CustomValidationException;
    ResponseData updateClientAllData(ClientDTO clientDTO) throws CustomValidationException;

}
