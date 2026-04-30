package co.icesi.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.courses.SubmissionDTO;
import co.icesi.auth.model.Activity;
import co.icesi.auth.model.Submission;
import co.icesi.auth.model.User;

@Component
@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    @Mapping(source = "activity", target = "activityId", qualifiedByName = "activityToId")
    @Mapping(source = "student", target = "studentId", qualifiedByName = "userToId")
    SubmissionDTO submissionToSubmissionDTO(Submission submission);

    @Named("activityToId")
    default Long activityToId(Activity activity) {
        return activity != null ? activity.getId() : null;
    }

    @Named("userToId")
    default Long userToId(User user) {
        return user != null ? user.getId() : null;
    }

    @Mapping(source = "activityId", target = "activity.id")
    @Mapping(source = "studentId", target = "student.id")
    Submission submissionDTOToSubmission(SubmissionDTO submissionDTO);
}
