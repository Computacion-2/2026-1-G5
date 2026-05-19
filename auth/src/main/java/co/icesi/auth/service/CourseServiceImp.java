package co.icesi.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.icesi.auth.model.Course;
import co.icesi.auth.model.User;
import co.icesi.auth.repository.CourseRepository;
import co.icesi.auth.repository.UserRepository;
import co.icesi.auth.service.interfaces.CourseService;

import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp implements CourseService{

    @Autowired
    private CourseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Course> getCourses() {
        return repository.findAll();
    }

    @Override
    public Course getCourseById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    @Override
    public Course addCourse(Course c) {
        if (c.getTeacher() != null && c.getTeacher().getId() != null) {
            User u = userRepository.findById(c.getTeacher().getId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
            c.setTeacher(u);
        }
        return repository.save(c);
    }

    @Override
    public Course editCourse(Course c) {
        Course course = repository.findById(c.getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        course.setName(c.getName() == null ? course.getName() : c.getName());
        course.setCode(c.getCode() == null ? course.getCode() : c.getCode());
        course.setCredits(c.getCredits() == null ? course.getCredits() : c.getCredits());
        course.setDescription(c.getDescription() == null ? course.getDescription() : c.getDescription());

        if (c.getTeacher() != null && c.getTeacher().getId() != null) {
            User u = userRepository.findById(c.getTeacher().getId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
            course.setTeacher(u);
        }
        return repository.save(course);
    }

    @Override
    public void deleteCourse(long id) {
        repository.deleteById(id);
    }

    @Override
    public Course addUserToCourse(long courseId, long userId) {
        Course course = repository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (course.getStudents() == null) {
            course.setStudents(new java.util.HashSet<>());
        }
        course.getStudents().add(user);
        return repository.save(course);
    }
    
    
    
}
