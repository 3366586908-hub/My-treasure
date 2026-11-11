package cn.cup.qs2023.gu2program.Service;

import cn.cup.qs2023.gu2program.Entity.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAppointmentsByUserId(Long userId);
    List<Appointment> getAppointmentsByCourseId(Long courseId, String status);
    void updateAppointmentStatus(Long id, String status);
    void deleteAppointment(Long id);
}