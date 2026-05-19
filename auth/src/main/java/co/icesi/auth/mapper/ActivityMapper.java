package co.icesi.auth.mapper;

import co.icesi.auth.dtos.activities.ActivityDTO;
import co.icesi.auth.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    @Mapping(source = "course.id", target = "courseId")
    ActivityDTO toDto(Activity activity);

    @Mapping(source = "courseId", target = "course.id")
    Activity toEntity(ActivityDTO activityDTO);
}
