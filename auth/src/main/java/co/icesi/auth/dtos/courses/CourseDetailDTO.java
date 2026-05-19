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
}
