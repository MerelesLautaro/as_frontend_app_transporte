package com.lautadev.susa_lautadev.model;

import java.time.LocalDate;

public class Transactions {
    private Long idTransaction;
    private TypeOfOperation typeOfOperation;
    private double amount;
    private LocalDate dateOfOperation;
    private Long idAccount;

    public Transactions() {
    }

    public Transactions(Long idTransaction, TypeOfOperation typeOfOperation, double amount, LocalDate dateOfOperation, Long idAccount) {
        this.idTransaction = idTransaction;
        this.typeOfOperation = typeOfOperation;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.idAccount = idAccount;
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

    public LocalDate getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(LocalDate dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }
}
