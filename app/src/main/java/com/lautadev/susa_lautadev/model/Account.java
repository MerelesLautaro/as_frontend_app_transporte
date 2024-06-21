package com.lautadev.susa_lautadev.model;

import java.time.LocalDate;

public class Account {
    private Long idAccount;
    private LocalDate dateOfCreation;
    private double balance;
    private String cvu;
    private String alias;
    private Long idUser;

    public Account() {
    }

    public Account(Long idAccount, LocalDate dateOfCreation, double balance, String cvu, String alias, Long idUser) {
        this.idAccount = idAccount;
        this.dateOfCreation = dateOfCreation;
        this.balance = balance;
        this.cvu = cvu;
        this.alias = alias;
        this.idUser = idUser;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
