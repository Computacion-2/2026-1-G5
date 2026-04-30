package co.icesi.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.courses.ActivityDTO;
import co.icesi.auth.model.Activity;
import co.icesi.auth.model.Course;

@Component
@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mapping(source = "course", target = "courseId", qualifiedByName = "courseToId")
    ActivityDTO activityToActivityDTO(Activity activity);

    @Named("courseToId")
    default Long courseToId(Course course) {
        return course != null ? course.getId() : null;
    }

    @Mapping(source = "courseId", target = "course.id")
    Activity activityDTOToActivity(ActivityDTO activityDTO);
}
