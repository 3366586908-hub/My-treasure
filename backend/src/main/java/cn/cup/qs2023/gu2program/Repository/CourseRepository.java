package cn.cup.qs2023.gu2program.Repository;


import cn.cup.qs2023.gu2program.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCoachIdAndStatus(Long coachId, Integer status);

    List<Course> findByCourseNameLikeAndStatus(String courseName, Integer status);
}