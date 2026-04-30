package co.icesi.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.model.Course;
import co.icesi.auth.model.User;

@Component
@Mapper(componentModel = "spring", uses={UserMapper.class})
public interface CourseMapper {

    /**
     * Mapea Course entity a CourseDTO
     */
    @Mapping(source = "teacher", target = "teacherId", qualifiedByName="teacherToId")
    CourseDTO courseToCourseDTO(Course course);
    
    @Mapping(source="students", target="students", qualifiedByName="userToString")
    CourseDetailDTO courseToDetailDTO(Course course);

    @Named("teacherToId")
    default Long teacherToId(User user) {
        return user != null ? user.getId() : null;
    }

    /**
     * Mapea CourseDTO a Course entity
     */
    @Mapping(source = "teacherId", target = "teacher.id")
    Course courseDTOToCourse(CourseDTO courseDTO);
}

