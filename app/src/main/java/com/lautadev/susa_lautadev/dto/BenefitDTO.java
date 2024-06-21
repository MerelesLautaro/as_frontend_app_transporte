package com.lautadev.susa_lautadev.dto;

import com.lautadev.susa_lautadev.model.DayWeek;

import java.time.LocalTime;
import java.util.List;

public class BenefitDTO {
    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<DayWeek> day;
    private Integer tickets;

    public BenefitDTO() {
    }

    public BenefitDTO(Long id, String name, LocalTime startTime, LocalTime endTime, List<DayWeek> day, Integer tickets) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<DayWeek> getDay() {
        return day;
    }

    public void setDay(List<DayWeek> day) {
        this.day = day;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }
}
