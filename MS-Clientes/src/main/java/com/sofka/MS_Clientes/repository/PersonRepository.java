package com.sofka.MS_Clientes.repository;


import com.sofka.MS_Clientes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByPersonDni(String personDni);
}
