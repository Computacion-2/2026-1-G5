package co.icesi.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.icesi.auth.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
