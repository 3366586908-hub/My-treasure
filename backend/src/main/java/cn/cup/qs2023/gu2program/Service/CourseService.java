package cn.cup.qs2023.gu2program.Service;

import cn.cup.qs2023.gu2program.Entity.Course;
import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getCoursesByCoachId(Long coachId);
    List<Course> getCoursesByName(String courseName);
    List<Course> getAllCourses(Integer status);
    void deleteCourse(Long id);
}