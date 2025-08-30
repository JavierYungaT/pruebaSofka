package com.sofka.MS_Cuentas_Movimientos.repository;


import com.sofka.MS_Cuentas_Movimientos.model.Account;
import com.sofka.MS_Cuentas_Movimientos.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findByAccountAccountNumber(String accountNumber);

    @Query("SELECT m FROM Movement m WHERE m.account.clientIdentification = ?1")
    List<Movement> findByAccountClientPersonDni(String userDni);

    @Query("SELECT m FROM Movement m WHERE m.account = ?1 AND m.movementDate BETWEEN ?2 AND ?3")
    List<Movement> findByAccountAndMovementDateBetween(Account account, LocalDateTime startDate, LocalDateTime endDate);
}
