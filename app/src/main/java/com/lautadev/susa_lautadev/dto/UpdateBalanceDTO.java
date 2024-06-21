package com.lautadev.susa_lautadev.dto;

import com.lautadev.susa_lautadev.model.TypeOfOperation;

public class UpdateBalanceDTO {
    private double amount;
    private TypeOfOperation typeOfOperation;
    private String aliasOrCvu;

    public UpdateBalanceDTO(double amount, TypeOfOperation typeOfOperation) {
        this.amount = amount;
        this.typeOfOperation = typeOfOperation;
    }

    public UpdateBalanceDTO() {
    }

    public UpdateBalanceDTO(double amount, TypeOfOperation typeOfOperation, String aliasOrCvu) {
        this.amount = amount;
        this.typeOfOperation = typeOfOperation;
        this.aliasOrCvu = aliasOrCvu;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TypeOfOperation getTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(TypeOfOperation typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }

    public String getAliasOrCvu() {
        return aliasOrCvu;
    }

    public void setAliasOrCvu(String aliasOrCvu) {
        this.aliasOrCvu = aliasOrCvu;
    }
}
