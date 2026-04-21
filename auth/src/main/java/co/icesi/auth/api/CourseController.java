package co.icesi.auth.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.auth.model.Course;
import co.icesi.auth.repository.CourseRepository;

@RestController
public class CourseController implements CourseApi{

    @Autowired
    private CourseRepository repo;

    @Override
    public List<Course> getCourses() {
        return repo.findAll();
        
    }

    @Override
    public ResponseEntity<?> saveCourse(Course c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCourse'");
    }
    
}
