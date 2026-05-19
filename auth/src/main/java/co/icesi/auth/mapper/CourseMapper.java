package co.icesi.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.model.Course;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CourseMapper {

    @Mapping(source = "teacher.id", target = "teacherId")
    CourseDTO courseToCourseDTO(Course course);
    
    @Mapping(source = "students", target = "students", qualifiedByName = "userToString")
    CourseDetailDTO courseToDetailDTO(Course course);

    @Mapping(source = "teacherId", target = "teacher.id")
    Course courseDTOToCourse(CourseDTO courseDTO);
}
