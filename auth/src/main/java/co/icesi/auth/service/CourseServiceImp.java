package co.icesi.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.icesi.auth.model.Course;
import co.icesi.auth.repository.CourseRepository;
import co.icesi.auth.service.interfaces.CourseService;

import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp implements CourseService{

    @Autowired
    private CourseRepository repository;

    @Override
    public List<Course> getCourses() {
        return repository.findAll();
    }

    @Override
    public Course addCourse(Course c) {
        return repository.save(c);
    }
    
    
    
}
