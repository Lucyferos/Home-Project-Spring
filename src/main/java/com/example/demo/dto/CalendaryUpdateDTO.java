package com.example.demo.dto;

import com.example.demo.repository.CalendarRepository;

public class CalendaryUpdateDTO extends CalendarCreateDTO{

    private String eventID;

    public CalendaryUpdateDTO(){
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
