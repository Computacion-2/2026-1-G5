package co.icesi.auth.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.model.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public interface CourseApi {
    
    @GetMapping
    public ResponseEntity<List<CourseDetailDTO>> getCourses();

    @PostMapping
    public ResponseEntity<CourseDetailDTO> saveCourse(@Valid @RequestBody CourseDTO courseDTO);

    @PostMapping("/{id}/students")
    public ResponseEntity<CourseDetailDTO> addUserToCourse(@PathVariable long id, @RequestBody User c);

    @PutMapping("/{id}")
    public ResponseEntity<CourseDetailDTO> updateCourse(@PathVariable long id, @Valid @RequestBody CourseDTO courseDTO);

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDTO> getCourseDetail(@PathVariable long id);
}


