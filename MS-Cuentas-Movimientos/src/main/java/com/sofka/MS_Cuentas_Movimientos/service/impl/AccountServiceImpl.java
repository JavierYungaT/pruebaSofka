package com.sofka.MS_Cuentas_Movimientos.service.impl;


import com.sofka.MS_Cuentas_Movimientos.dto.ResponseData;
import com.sofka.MS_Cuentas_Movimientos.dto.account.AccountDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.account.AccountNumberDTO;
import com.sofka.MS_Cuentas_Movimientos.dto.account.SaveAccountDTO;
import com.sofka.MS_Cuentas_Movimientos.enums.ErrorMessagesEnum;
import com.sofka.MS_Cuentas_Movimientos.enums.SuccessCodesEnum;
import com.sofka.MS_Cuentas_Movimientos.enums.SuccessMessagesEnum;
import com.sofka.MS_Cuentas_Movimientos.exceptions.CustomValidationException;
import com.sofka.MS_Cuentas_Movimientos.exceptions.GlobalExceptionHandler;
import com.sofka.MS_Cuentas_Movimientos.exceptions.ResourceNotFoundException;
import com.sofka.MS_Cuentas_Movimientos.model.Account;
import com.sofka.MS_Cuentas_Movimientos.repository.AccountRepository;
import com.sofka.MS_Cuentas_Movimientos.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final KafkaTemplate<String, Long> kafkaTemplate;
    private final AccountRepository accountRepository;


    public List<AccountDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts != null && !accounts.isEmpty()) {
            return accounts.stream()
                    .map(account -> new AccountDTO(
                            account.getAccountNumber(),
                            account.getAccountType(),
                            account.getAccountBalance(),
                            account.getClientIdentification(),
                            account.getAccountState()))
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException(ErrorMessagesEnum.ACCOUNTS_NOT_FOUND.getMessage());
        }
    }


    public AccountDTO getAccountByNumber(AccountNumberDTO accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessagesEnum.ACCOUNT_NOT_FOUND.getMessage()));

        return new AccountDTO(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAccountBalance(),
                account.getClientIdentification(),
                account.getAccountState());
    }

    public ResponseData saveAccount(SaveAccountDTO accountDTO) throws CustomValidationException {


        Optional<Account> accountExist = accountRepository.findByAccountNumber(accountDTO.getNumeroCuenta());
        if (accountExist.isPresent()) {
            throw new CustomValidationException(ErrorMessagesEnum.ACCOUNT_ALREADY_EXISTS.getMessage());
        }

        Account account = new Account();
        account.setAccountNumber(accountDTO.getNumeroCuenta());
        account.setAccountType(accountDTO.getTipoCuenta());
        account.setAccountBalance(accountDTO.getSaldoInicial());
        account.setAccountState(accountDTO.getEstado());
        account.setClientIdentification(accountDTO.getIdentificacion());
        account.setClientName(accountDTO.getNombre());
        accountRepository.save(account);
        return new ResponseData(
                SuccessMessagesEnum.SUCCESSFULLY_CREATED.getMessage(),
                SuccessCodesEnum.SUCCESS_CODE.getMessage(),
                SuccessMessagesEnum.STATUS_OK.getMessage()
        );
    }

    public ResponseData updateClient(Integer accountId, Map<String, Object> fields) throws CustomValidationException {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new CustomValidationException(ErrorMessagesEnum.CLIENT_NOT_FOUND.getMessage());
        }
        if (fields.containsKey("accountNumber")) {
            throw new CustomValidationException(ErrorMessagesEnum.FIELD_MODIFIED_NOT_ALLOWED.getMessage());
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Account.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, account.get(), value);
            } else {
                throw new ResourceNotFoundException(ErrorMessagesEnum.FIELD_NOT_FOUND.getMessage() + " Field: " + key);
            }
        });
        accountRepository.save(account.get());
        return new ResponseData(
                SuccessMessagesEnum.SUCCESSFULLY_UPDATED.getMessage(),
                SuccessCodesEnum.SUCCESS_CODE.getMessage(),
                SuccessMessagesEnum.STATUS_OK.getMessage()
        );
    }

    public ResponseData updateAccountAllData(AccountDTO accountDTO) throws CustomValidationException {

        Optional<Account> accountExist = accountRepository.findByAccountNumber(accountDTO.getNumeroCuenta());
        if (accountExist.isPresent()) {
            throw new CustomValidationException(ErrorMessagesEnum.ACCOUNT_ALREADY_EXISTS.getMessage());
        }

        Account existingAccount = new Account();
        existingAccount.setAccountNumber(accountDTO.getNumeroCuenta());
        existingAccount.setAccountType(accountDTO.getTipoCuenta());
        existingAccount.setAccountBalance(accountDTO.getSaldoInicial());
        existingAccount.setAccountState(accountDTO.getEstado());
        accountRepository.save(existingAccount);

        return new ResponseData(
                SuccessMessagesEnum.SUCCESSFULLY_UPDATED.getMessage(),
                SuccessCodesEnum.SUCCESS_CODE.getMessage(),
                SuccessMessagesEnum.STATUS_OK.getMessage()
        );
    }

    public ResponseData deleteAccount(AccountNumberDTO accountNumberDTO) throws CustomValidationException {
        Optional<Account> accountExist = accountRepository.findByAccountNumber(accountNumberDTO.getAccountNumber());
        if (accountExist.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessagesEnum.ACCOUNT_NOT_FOUND.getMessage());
        }

        accountRepository.delete(accountExist.get());
        return new ResponseData(
                SuccessMessagesEnum.SUCCESSFULLY_DELETED.getMessage(),
                SuccessCodesEnum.SUCCESS_CODE.getMessage(),
                SuccessMessagesEnum.STATUS_OK.getMessage()
        );
    }
}
