package com.sofka.MS_Cuentas_Movimientos.controller;

import com.sofka.MS_Cuentas_Movimientos.dto.ResponseData;
import com.sofka.MS_Cuentas_Movimientos.dto.account.AccountDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.account.AccountNumberDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.account.SaveAccountDTO;
import com.sofka.MS_Cuentas_Movimientos.exceptions.CustomValidationException;
import com.sofka.MS_Cuentas_Movimientos.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/findAllAccounts")
    public ResponseEntity<List<AccountDTO>> getAllClients() {
        List<AccountDTO> clients = accountService.findAllAccounts();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/getAccountByNumber")
    public ResponseEntity<AccountDTO> getAccountByNumber(@RequestBody @Valid AccountNumberDTO accountNumber) {
        AccountDTO account = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/saveAccount")
    public ResponseEntity<ResponseData> saveAccount(@RequestBody @Valid SaveAccountDTO accountDTO) throws CustomValidationException {
        ResponseData account = accountService.saveAccount(accountDTO);
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/updateAccount/{accountId}")
    public ResponseEntity<ResponseData> updateClient(@PathVariable Integer accountId, @RequestBody Map<String, Object> fields) throws CustomValidationException, CustomValidationException {
        ResponseData account = accountService.updateClient(accountId, fields);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/updateAccountAllData")
    public ResponseEntity<ResponseData> updateAccountAllData(@RequestBody @Valid AccountDTO accountDTO) throws CustomValidationException {
        ResponseData account = accountService.updateAccountAllData(accountDTO);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseData> deleteAccount(@RequestBody @Valid AccountNumberDTO accountNumberDTO) throws CustomValidationException {
        ResponseData account = accountService.deleteAccount(accountNumberDTO);
        return ResponseEntity.ok(account);
    }

}
