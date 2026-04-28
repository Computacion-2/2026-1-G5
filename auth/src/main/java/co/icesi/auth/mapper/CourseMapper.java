package co.icesi.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.model.Course;
import co.icesi.auth.model.User;

@Component
@Mapper(componentModel = "spring", uses={UserMapper.class})
public interface CourseMapper {

    /**
     * Mapea Course entity a CourseDTO para solicitudes de creación/actualización
     */
    @Mapping(source = "teacher", target = "teacherId", qualifiedByName="teacherToDto")
    CourseDTO courseToCourseDTO(Course course);

    @Named("teacherToId")
    public default Long teacherToId(User user){
        return user.getId();
    }
    /**
     * Mapea CourseDTO a Course entity
     */
    @Mapping(source = "teacherId", target = "teacher.id")
    Course courseDTOToCourse(CourseDTO courseDTO);

}
