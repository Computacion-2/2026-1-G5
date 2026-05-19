package co.icesi.auth.service.interfaces;

import co.icesi.auth.model.Submission;
import java.util.List;

public interface SubmissionService {
    Submission createSubmission(Submission submission, Long activityId, Long studentId);
    Submission updateSubmission(Long id, Submission submission);
    void deleteSubmission(Long id);
    Submission getSubmission(Long id);
    List<Submission> getSubmissionsByActivity(Long activityId);
    Submission gradeSubmission(Long id, Double grade, String feedback);
}
