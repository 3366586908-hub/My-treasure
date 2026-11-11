package cn.cup.qs2023.gu2program.Controller;

import cn.cup.qs2023.gu2program.Entity.Course;
import cn.cup.qs2023.gu2program.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }


    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<Course>> getCoursesByCoachId(@PathVariable Long coachId) {
        return ResponseEntity.ok(courseService.getCoursesByCoachId(coachId));
    }


    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String name) {
        return ResponseEntity.ok(courseService.getCoursesByName(name));
    }


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) Integer status) {
        return ResponseEntity.ok(courseService.getAllCourses(status));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}