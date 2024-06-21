package com.lautadev.susa_lautadev.model;

import java.time.LocalDate;

public class User {
    private Long idUser;
    private String name;
    private String surname;
    private String cel;
    private String cuit;
    private String dni;
    private String dateOfBirth;
    private String email;
    private String password;
    private String departament;
    private String city;
    private String address;
    private String zipCode;
    private Long idBenefit;
    private int tickets;

    public User() {
    }

    public User(Long idUser, String name, String surname, String cel, String cuit, String dni,
                String dateOfBirth, String email, String password, String departament,
                String city, String address, String zipCode, Long idBenefit, int tickets) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.cel = cel;
        this.cuit = cuit;
        this.dni = dni;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.departament = departament;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.idBenefit = idBenefit;
        this.tickets = tickets;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getIdBenefit() {
        return idBenefit;
    }

    public void setIdBenefit(Long idBenefit) {
        this.idBenefit = idBenefit;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
