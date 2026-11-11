package cn.cup.qs2023.gu2program.Repository;


import cn.cup.qs2023.gu2program.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUserIdOrderByAppointTimeDesc(Long userId);

    List<Appointment> findByCourseIdAndAppointStatus(Long courseId, String status);
}
