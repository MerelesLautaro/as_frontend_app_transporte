package com.lautadev.susa_lautadev.dto;

import com.lautadev.susa_lautadev.model.User;

public class UserDTO {
    private Long idUser;
    private String name;
    private String surname;
    private String dni;
    private int tickets;
    private BenefitDTO benefit;

    public UserDTO() {
    }

    public UserDTO(User user, BenefitDTO benefitDTO) {
        this.idUser = user.getIdUser();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.dni = user.getDni();
        this.tickets = user.getTickets();
        this.benefit = benefitDTO;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public BenefitDTO getBenefit() {
        return benefit;
    }

    public void setBenefit(BenefitDTO benefit) {
        this.benefit = benefit;
    }
}
