package cn.cup.qs2023.gu2program.Service.Impl;


import cn.cup.qs2023.gu2program.Entity.Course;
import cn.cup.qs2023.gu2program.Repository.CourseRepository;
import cn.cup.qs2023.gu2program.Service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not exists：ID=" + id));
    }

    @Override
    public List<Course> getCoursesByCoachId(Long coachId) {
        return courseRepository.findByCoachIdAndStatus(coachId, 1);
    }

    @Override
    public List<Course> getCoursesByName(String courseName) {
        if (!StringUtils.hasText(courseName)) {
            return getAllCourses(1);
        }
        return courseRepository.findByCourseNameLikeAndStatus("%" + courseName + "%", 1);
    }

    @Override
    public List<Course> getAllCourses(Integer status) {
        return courseRepository.findAll().stream()
                .filter(course -> status == null || course.getStatus().equals(status))
                .toList();
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("not exists：ID=" + id);
        }
        courseRepository.deleteById(id);
    }
}