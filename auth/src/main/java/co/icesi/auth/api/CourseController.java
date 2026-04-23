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
public class CourseController implements CourseApi{

    @Autowired
    private CourseService service;

    @Override
    public List<Course> getCourses() {
        return service.getCourses();
        
    }

    @Override
    public ResponseEntity<Course> saveCourse(Course c) {
        c = service.addCourse(c);
        return ResponseEntity.ok(c);
    }

    @Override
    public ResponseEntity<Course> addUserToCourse(long id, User c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUserToCourse'");
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
    public List<Course> getCourseDetail(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCourseDetail'");
    }
    
}
