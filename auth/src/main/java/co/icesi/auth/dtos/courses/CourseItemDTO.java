package co.icesi.auth.dtos.courses;

import lombok.Data;

@Data
public class CourseItemDTO {

    private String name;

    private String description;

    private String code;

    private Integer credits;

    private String teacherName;
}
