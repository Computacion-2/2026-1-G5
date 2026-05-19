package co.icesi.auth.mapper;

import co.icesi.auth.dtos.submissions.SubmissionDTO;
import co.icesi.auth.model.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {
    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "student.id", target = "studentId")
    SubmissionDTO toDto(Submission submission);

    @Mapping(source = "activityId", target = "activity.id")
    @Mapping(source = "studentId", target = "student.id")
    Submission toEntity(SubmissionDTO submissionDTO);
}
