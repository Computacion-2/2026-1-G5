package co.icesi.auth.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.mapper.CourseMapper;
import co.icesi.auth.model.Course;
import co.icesi.auth.model.User;
import co.icesi.auth.service.interfaces.CourseService;
import jakarta.validation.Valid;

@RestController
public class CourseController implements CourseApi {

    @Autowired
    private CourseService service;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ResponseEntity<List<CourseDetailDTO>> getCourses() {
        try {
            List<Course> courses = service.getCourses();
            List<CourseDetailDTO> dtos = null;
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> saveCourse( CourseDTO courseDTO) {
        try {
            // Convertir DTO a Entity
            Course course = new Course();
            course.setName(courseDTO.getName());
            course.setDescription(courseDTO.getDescription());
            course.setCode(courseDTO.getCode());
            course.setCredits(courseDTO.getCredits());
            
            // El servicio debe manejar el profesor
            Course savedCourse = service.addCourse(course);
            
            // Convertir Entity a DTO de respuesta
            CourseDetailDTO response = courseMapper.courseToDetailDTO(savedCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> addUserToCourse(long id, User student) {
        try {
            Course course = service.addUserToCourse(id, student.getId());
            CourseDetailDTO response = courseMapper.courseToDetailDTO(course);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> updateCourse(long id, @Valid CourseDTO courseDTO) {
        try {
            // Convertir DTO a Entity
            Course course = new Course();
            course.setId(id);
            course.setName(courseDTO.getName());
            course.setDescription(courseDTO.getDescription());
            course.setCode(courseDTO.getCode());
            course.setCredits(courseDTO.getCredits());
            
            // Actualizar en la base de datos
            Course updated = service.editCourse(course);
            
            // Convertir a DTO de respuesta
            CourseDetailDTO response = courseMapper.courseToDetailDTO(updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> getCourseDetail(long id) {
        try {
            List<Course> courses = service.getCourses();
            Course course = courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            
            CourseDetailDTO response = courseMapper.courseToDetailDTO(course);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
