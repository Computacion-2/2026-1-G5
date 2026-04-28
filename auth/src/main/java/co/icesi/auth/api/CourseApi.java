package co.icesi.auth.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.icesi.auth.model.*;

@RestController
@RequestMapping("/api/courses")
public interface CourseApi {
    
    @GetMapping
    public List<Course> getCourses();

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course c);

    @PostMapping("/{id}/students")
    public ResponseEntity<Course> addUserToCourse(@PathVariable long id, @RequestBody User c);

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable long id, @RequestBody Course c);

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseDetail(@PathVariable long id);
}


