package co.icesi.auth.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.mapper.CourseMapper;
import co.icesi.auth.model.Course;
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
            List<CourseDetailDTO> dtos = courses.stream()
                .map(courseMapper::courseToDetailDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> saveCourse(CourseDTO courseDTO) {
        try {
            Course course = courseMapper.courseDTOToCourse(courseDTO);
            Course savedCourse = service.addCourse(course);
            CourseDetailDTO response = courseMapper.courseToDetailDTO(savedCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> addUserToCourse(long id, long userId) {
        try {
            Course course = service.addUserToCourse(id, userId);
            CourseDetailDTO response = courseMapper.courseToDetailDTO(course);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> updateCourse(long id, @Valid CourseDTO courseDTO) {
        try {
            Course course = courseMapper.courseDTOToCourse(courseDTO);
            course.setId(id);
            Course updated = service.editCourse(course);
            CourseDetailDTO response = courseMapper.courseToDetailDTO(updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CourseDetailDTO> getCourseDetail(long id) {
        try {
            Course course = service.getCourseById(id);
            CourseDetailDTO response = courseMapper.courseToDetailDTO(course);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCourse(long id) {
        try {
            service.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
