package com.example.demo.controller;

import com.example.demo.dto.CalendarCreateDTO;
import com.example.demo.dto.CalendaryUpdateDTO;
import com.example.demo.repository.CalendarRepository;
import com.google.api.services.calendar.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/calendar")
public class CalendarController {

    private CalendarRepository calendarRepository;

    public CalendarController(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @PostMapping
    public ResponseEntity<Event> insertEvent(@RequestBody CalendarCreateDTO calendarCreateDTO) throws Exception {
        Event event  = calendarRepository.createEvent(calendarCreateDTO);

        return ResponseEntity.ok(event);
    }

    @PutMapping
    public ResponseEntity<Event> insertEvent(@RequestBody CalendaryUpdateDTO calendaryUpdateDTO) throws Exception {
        Event event = calendarRepository.updateEvent(calendaryUpdateDTO);

        return ResponseEntity.ok(event);
    }
}
