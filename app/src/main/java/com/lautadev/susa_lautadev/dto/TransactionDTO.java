package com.lautadev.susa_lautadev.dto;

import com.lautadev.susa_lautadev.model.Transaction;
import com.lautadev.susa_lautadev.model.TypeOfOperation;

import java.time.LocalDate;

public class TransactionDTO {
    private Long idTransaction;
    private TypeOfOperation typeOfOperation;
    private double amount;
    private String dateOfOperation;
    private AccountDTO accountDTO;

    public TransactionDTO(AccountDTO accountDTO, Transaction transaction) {
        this.idTransaction = transaction.getIdTransaction();
        this.typeOfOperation = transaction.getTypeOfOperation();
        this.amount = transaction.getAmount();
        this.dateOfOperation = transaction.getDateOfOperation();
        this.accountDTO = accountDTO;
    }

    public TransactionDTO() {
    }

    public TransactionDTO(Long idTransaction, TypeOfOperation typeOfOperation, double amount, String dateOfOperation, AccountDTO accountDTO) {
        this.idTransaction = idTransaction;
        this.typeOfOperation = typeOfOperation;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.accountDTO = accountDTO;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public TypeOfOperation getTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(TypeOfOperation typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }
}
