package cn.cup.qs2023.gu2program.Controller;


import cn.cup.qs2023.gu2program.Entity.Appointment;

import cn.cup.qs2023.gu2program.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppoint = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(savedAppoint, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUserId(userId));
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByCourseId(
            @PathVariable Long courseId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByCourseId(courseId, status));
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        appointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
