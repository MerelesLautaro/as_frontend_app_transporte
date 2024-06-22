package com.lautadev.susa_lautadev.model;

import java.time.LocalTime;
import java.util.List;

public class Benefit {
    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private List<DayWeek> day;
    private Integer tickets;

    public Benefit() {
    }

    public Benefit(Long id, String name, String startTime, String endTime, List<DayWeek> day, Integer tickets) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
