package com.lautadev.susa_lautadev.model;

public class Transaction {
    private Long idTransaction;
    private TypeOfOperation typeOfOperation;
    private double amount;
    private String dateOfOperation;
    private Long idAccount;

    public Transaction() {
    }

    public Transaction(Long idTransaction, TypeOfOperation typeOfOperation, double amount, String dateOfOperation, Long idAccount) {
        this.idTransaction = idTransaction;
        this.typeOfOperation = typeOfOperation;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.idAccount = idAccount;
    }

    public Transaction(TypeOfOperation typeOfOperation, double amount, String dateOfOperation, Long idAccount) {
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

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }
}
