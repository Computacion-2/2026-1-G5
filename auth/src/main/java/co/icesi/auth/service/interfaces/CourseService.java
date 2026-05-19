package co.icesi.auth.service.interfaces;

import java.util.List;

import co.icesi.auth.model.Course;

public interface  CourseService {
    
    public List<Course> getCourses();

    public Course getCourseById(long id);

    public Course addCourse(Course c);

    public Course editCourse(Course c);

    public void deleteCourse(long id);

    public Course addUserToCourse(long courseId, long userId);
}
