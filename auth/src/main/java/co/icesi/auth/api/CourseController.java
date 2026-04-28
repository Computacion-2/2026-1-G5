package co.icesi.auth.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.model.Course;
import co.icesi.auth.model.User;
import co.icesi.auth.service.interfaces.CourseService;

@RestController
public class CourseController implements CourseApi {

    @Autowired
    private CourseService service;

    @Override
    public List<Course> getCourses() {
        return service.getCourses();
    }

    @Override
    public ResponseEntity<Course> saveCourse(Course c) {
        try {
            c = service.addCourse(c);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Course> addUserToCourse(long id, User c) {
        try {
            Course course = service.addUserToCourse(id, c.getId());
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> updateCourse(long id, Course c) {
        try {
            c.setId(id);
            Course response = service.editCourse(c);
            CourseDetailDTO dto = CourseDetailDTO.fromCourse(response);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("reason", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getCourseDetail(long id) {
        try {
            // Obtener el curso por ID
            List<Course> courses = service.getCourses();
            Course course = courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
            CourseDetailDTO dto = CourseDetailDTO.fromCourse(course);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("reason", e.getMessage()));
        }
    }
}
