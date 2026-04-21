package co.icesi.auth.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.auth.model.Course;

@RestController
@RequestMapping("/api/courses")
public interface CourseApi {
    
    @GetMapping
    public List<Course> getCourses();

    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody Course c);
}
