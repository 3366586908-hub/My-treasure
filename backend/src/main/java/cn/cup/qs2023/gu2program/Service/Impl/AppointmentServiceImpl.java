package cn.cup.qs2023.gu2program.Service.Impl;


import cn.cup.qs2023.gu2program.Entity.Appointment;
import cn.cup.qs2023.gu2program.Repository.AppointmentRepository;
import cn.cup.qs2023.gu2program.Service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        appointment.setUpdateTime(LocalDateTime.now());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not exists：ID=" + id));
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepository.findByUserIdOrderByAppointTimeDesc(userId);
    }

    @Override
    public List<Appointment> getAppointmentsByCourseId(Long courseId, String status) {
        if (status == null) {
            status = "waiting confirm";
        }
        return appointmentRepository.findByCourseIdAndAppointStatus(courseId, status);
    }

    @Override
    public void updateAppointmentStatus(Long id, String status) {
        Appointment appointment = getAppointmentById(id);
        appointment.setAppointStatus(status);
        appointment.setUpdateTime(LocalDateTime.now());
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("not exists：ID=" + id);
        }
        appointmentRepository.deleteById(id);
    }
}
