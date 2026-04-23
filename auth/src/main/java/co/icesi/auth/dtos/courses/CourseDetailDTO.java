package co.icesi.auth.dtos.courses;

import java.util.List;

import co.icesi.auth.dtos.users.UserTeacherDTO;
import co.icesi.auth.model.Course;
import lombok.Data;

@Data
public class CourseDetailDTO {
    private Long id;

    private String name;

    private String description;

    private String code;

    private Integer credits;

    private UserTeacherDTO teacher;

    private List<String> students;

    public static CourseDetailDTO fromCourse(Course course){
        CourseDetailDTO dto = new CourseDetailDTO();

        dto.setCode(course.getCode());
        dto.setCredits(course.getCredits());
        dto.setDescription(course.getDescription());
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setStudents(course.getStudents().stream().map(u -> u.getFirstName()).toList());
        dto.setTeacher(UserTeacherDTO.fromEntity(course.getTeacher()));

        return dto;
    }
}
