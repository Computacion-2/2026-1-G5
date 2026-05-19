package co.icesi.auth.api;

import co.icesi.auth.dtos.submissions.SubmissionDTO;
import co.icesi.auth.mapper.SubmissionMapper;
import co.icesi.auth.model.Submission;
import co.icesi.auth.service.interfaces.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SubmissionController implements SubmissionApi {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Override
    public ResponseEntity<SubmissionDTO> createSubmission(SubmissionDTO submissionDTO) {
        Submission submission = submissionMapper.toEntity(submissionDTO);
        Submission created = submissionService.createSubmission(submission, submissionDTO.getActivityId(), submissionDTO.getStudentId());
        return ResponseEntity.ok(submissionMapper.toDto(created));
    }

    @Override
    public ResponseEntity<SubmissionDTO> updateSubmission(Long id, SubmissionDTO submissionDTO) {
        Submission submission = submissionMapper.toEntity(submissionDTO);
        Submission updated = submissionService.updateSubmission(id, submission);
        return ResponseEntity.ok(submissionMapper.toDto(updated));
    }

    @Override
    public ResponseEntity<Void> deleteSubmission(Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SubmissionDTO> getSubmission(Long id) {
        Submission submission = submissionService.getSubmission(id);
        return ResponseEntity.ok(submissionMapper.toDto(submission));
    }

    @Override
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByActivity(Long activityId) {
        List<Submission> submissions = submissionService.getSubmissionsByActivity(activityId);
        List<SubmissionDTO> dtos = submissions.stream()
                .map(submissionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<SubmissionDTO> gradeSubmission(Long id, Double grade, String feedback) {
        Submission submission = submissionService.gradeSubmission(id, grade, feedback);
        return ResponseEntity.ok(submissionMapper.toDto(submission));
    }
}
