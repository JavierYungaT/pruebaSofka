package com.sofka.MS_Clientes.repository;

import com.sofka.MS_Clientes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientRepository extends JpaRepository<Client, Integer> {


}
