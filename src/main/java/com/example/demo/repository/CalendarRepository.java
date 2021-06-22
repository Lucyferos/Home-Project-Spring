package com.example.demo.repository;

import com.example.demo.dto.CalendarCreateDTO;
import com.example.demo.dto.CalendaryUpdateDTO;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarRepository {
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";
    private static final String CALENDAR_ID = "oge0vfrai0q3tl2kebgijrkqd0";
    private static final String TIMEZONE = "Europe/Warsaw";

    private Calendar getAuthorizedCalendar() throws Exception {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleCredential credential = GoogleCredential.fromStream(CalendarRepository.class.getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH)).createScoped(SCOPES);
        Calendar calendar = new Calendar.Builder(httpTransport, jsonFactory, credential).setApplicationName("public").build();
        return calendar;
    }

    public Event createEvent(CalendarCreateDTO calendarCreateDTO) throws Exception {
        Calendar calendar = this.getAuthorizedCalendar();

        Event event = new Event();
        addInformationsToEvent(event, calendarCreateDTO);
        event = calendar.events().insert(CALENDAR_ID, event).execute();

        return event;
    }

    public Event updateEvent(CalendaryUpdateDTO calendaryUpdateDTO) throws Exception {
        Calendar calendar = this.getAuthorizedCalendar();

        Event event = calendar.events().get(CALENDAR_ID, calendaryUpdateDTO.getEventID()).execute();
        addInformationsToEvent(event, calendaryUpdateDTO);
        event = calendar.events().update(CALENDAR_ID, calendaryUpdateDTO.getEventID(), event).execute();

        return event;
    }

    public List<Event> getPatientEvents(String keycloakId) throws Exception {
        Calendar calendar = this.getAuthorizedCalendar();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendar.events().list(CALENDAR_ID)
                .setMaxResults(1500)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events.getItems()
                .stream()
                .filter(event -> event.getDescription() != null && event.getDescription().equals(keycloakId))
                .collect(Collectors.toList());
    }

    private void addInformationsToEvent(Event event, CalendarCreateDTO calendarCreateDTO) {
        event.setSummary(calendarCreateDTO.getTitle())
                .setLocation(calendarCreateDTO.getLocalization());
               // .setDescription(calendarCreateDTO.getKeycloakId())

        EventDateTime start = new EventDateTime()
                .setDateTime(new DateTime(calendarCreateDTO.getStartDate().toString()))
                .setTimeZone(TIMEZONE);
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new DateTime(calendarCreateDTO.getStartDate().plusHours(1).toString()))
                .setTimeZone(TIMEZONE);
        event.setEnd(end);

    }
}
