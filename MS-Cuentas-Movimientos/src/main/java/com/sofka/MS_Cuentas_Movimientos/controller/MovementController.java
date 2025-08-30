package com.sofka.MS_Cuentas_Movimientos.controller;


import com.sofka.MS_Cuentas_Movimientos.dto.ResponseData;
import com.sofka.MS_Cuentas_Movimientos.dto.UserDniDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.account.AccountNumberDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.movement.MovementDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.movement.MovementId;
import com.sofka.MS_Cuentas_Movimientos.dto.movement.MovementRequestDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.reports.ClientReportDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.reports.ReportRequestDTO;
import com.sofka.MS_Cuentas_Movimientos.exceptions.CustomValidationException;
import com.sofka.MS_Cuentas_Movimientos.service.MovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movement")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;


    @PostMapping("/getMovementByAccount")
    public ResponseEntity<List<MovementDTO>> getAccountByNumber(@RequestBody @Valid AccountNumberDTO accountNumber) {
        List<MovementDTO> account = movementService.findMovementByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/getAllMovements")
    public ResponseEntity<List<MovementDTO>> getAllMovements() throws CustomValidationException {
        List<MovementDTO> movements = movementService.findAll();
        if (movements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movements);
    }

    @PostMapping("/getMovementByDni")
    public ResponseEntity<List<MovementDTO>> getMovementByDni(@RequestBody @Valid UserDniDTO userDniDTO) throws CustomValidationException {
        List<MovementDTO> movements = movementService.findMovementByDni(userDniDTO);
        return ResponseEntity.ok(movements);
    }

    @PostMapping("/makeMovement")
    public ResponseEntity<ResponseData> makeMovement(@RequestBody @Valid MovementRequestDTO movementDTO) throws CustomValidationException {
        ResponseData response = movementService.handleMovement(movementDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/generateReport")
    public ResponseEntity<ClientReportDTO> generateReport(@RequestBody @Valid ReportRequestDTO reportRequestDTO) throws CustomValidationException {
        ClientReportDTO report = movementService.generateReport(reportRequestDTO);
        return ResponseEntity.ok(report);
    }

    @PatchMapping("/updateMovement/{movementId}")
    public ResponseEntity<ResponseData> updateMovement(@PathVariable Integer movementId, @RequestBody Map<String, Object> fields) throws CustomValidationException {
        ResponseData response = movementService.updateMovement(movementId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteMovement")
    public ResponseEntity<ResponseData> deleteMovement(@RequestBody MovementId movementId) throws CustomValidationException {
        ResponseData response = movementService.deleteMovement(movementId);
        return ResponseEntity.ok(response);
    }


}