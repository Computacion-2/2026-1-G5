package co.icesi.auth.service.interfaces;

import java.util.List;

import co.icesi.auth.model.Course;

public interface  CourseService {
    
    public List<Course> getCourses();

    public Course addCourse(Course c);
}
