package com.example.demo.dto;

import java.time.LocalDateTime;

public class CalendarCreateDTO {

    private LocalDateTime startDate;
    private String localization;
    private String title;

    public CalendarCreateDTO() {
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
