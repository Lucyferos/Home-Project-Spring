package com.example.demo.mapper;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {PatientMapper.class})
public interface AppointmentMapper {
    @Mapping(source = "appointment.patient" , target = "patientDTO")
    AppointmentDTO appointmentToAppointmentDto(Appointment appointment);
    Appointment appointmentDtoToAppointment(AppointmentDTO appointmentDTO);
}
