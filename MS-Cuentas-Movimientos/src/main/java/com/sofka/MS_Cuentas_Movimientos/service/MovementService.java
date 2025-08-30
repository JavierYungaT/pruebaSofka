package com.sofka.MS_Cuentas_Movimientos.service;

import com.sofka.MS_Cuentas_Movimientos.dto.ResponseData;
import com.sofka.MS_Cuentas_Movimientos.dto.UserDniDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.account.AccountNumberDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.movement.MovementDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.movement.MovementId;
import com.sofka.MS_Cuentas_Movimientos.dto.movement.MovementRequestDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.reports.ClientReportDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.reports.ReportRequestDTO;
import com.sofka.MS_Cuentas_Movimientos.exceptions.CustomValidationException;

import java.util.List;
import java.util.Map;

public interface MovementService {

    List<MovementDTO> findMovementByAccountNumber(AccountNumberDTO accountNumberDTO);
    List<MovementDTO> findAll() throws CustomValidationException;
    List<MovementDTO> findMovementByDni(UserDniDTO userDniDTO) throws CustomValidationException;
    ResponseData handleMovement(MovementRequestDTO movementDTO) throws CustomValidationException;
    ClientReportDTO generateReport(ReportRequestDTO reportRequestDTO) throws CustomValidationException;
    ResponseData updateMovement(Integer userId, Map<String, Object> fields) throws CustomValidationException;
    ResponseData deleteMovement(MovementId movementId) throws CustomValidationException;


}
