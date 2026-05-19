package co.icesi.auth.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public interface CourseApi {
    
    @GetMapping
    @PreAuthorize("hasAuthority('COURSE_READ')")
    public ResponseEntity<List<CourseDetailDTO>> getCourses();

    @PostMapping
    @PreAuthorize("hasAuthority('COURSE_CREATE')")
    public ResponseEntity<CourseDetailDTO> saveCourse(@Valid @RequestBody CourseDTO courseDTO);

    @PostMapping("/{id}/enroll/{userId}")
    @PreAuthorize("hasAuthority('COURSE_ENROLL')")
    public ResponseEntity<CourseDetailDTO> addUserToCourse(@PathVariable long id, @PathVariable long userId);

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('COURSE_UPDATE')")
    public ResponseEntity<CourseDetailDTO> updateCourse(@PathVariable long id, @Valid @RequestBody CourseDTO courseDTO);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('COURSE_READ')")
    public ResponseEntity<CourseDetailDTO> getCourseDetail(@PathVariable long id);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('COURSE_DELETE')")
    public ResponseEntity<Void> deleteCourse(@PathVariable long id);
}
