package co.icesi.auth.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.courses.CourseDTO;
import co.icesi.auth.dtos.courses.CourseDetailDTO;
import co.icesi.auth.dtos.users.UserTeacherDTO;
import co.icesi.auth.model.Course;
import co.icesi.auth.model.User;

@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {

    /**
     * Mapea Course entity a CourseDTO para solicitudes de creación/actualización
     */
    @Mapping(source = "teacher.id", target = "teacherId")
    CourseDTO courseToCourseDTO(Course course);

    /**
     * Mapea CourseDTO a Course entity
     */
    @Mapping(source = "teacherId", target = "teacher.id")
    Course courseDTOToCourse(CourseDTO courseDTO);

    /**
     * Mapea Course entity a CourseDetailDTO para respuestas detalladas
     */
    default CourseDetailDTO courseToDetailDTO(Course course) {
        if (course == null) {
            return null;
        }

        CourseDetailDTO dto = new CourseDetailDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setCode(course.getCode());
        dto.setCredits(course.getCredits());

        // Mapear teacher
        if (course.getTeacher() != null) {
            dto.setTeacher(UserTeacherDTO.fromEntity(course.getTeacher()));
        }

        // Mapear students: Set<User> a List<String>
        if (course.getStudents() != null && !course.getStudents().isEmpty()) {
            List<String> studentNames = course.getStudents().stream()
                    .map(User::getFirstName)
                    .collect(Collectors.toList());
            dto.setStudents(studentNames);
        }

        return dto;
    }

    /**
     * Mapea una lista de Course a una lista de CourseDetailDTO
     */
    default List<CourseDetailDTO> courseListToDetailDTOList(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        return courses.stream()
                .map(this::courseToDetailDTO)
                .collect(Collectors.toList());
    }

    /**
     * Mapea una lista de Course a una lista de CourseDTO
     */
    default List<CourseDTO> courseListToDTOList(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        return courses.stream()
                .map(this::courseToCourseDTO)
                .collect(Collectors.toList());
    }
}
