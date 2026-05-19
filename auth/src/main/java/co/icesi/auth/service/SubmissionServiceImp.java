package co.icesi.auth.service;

import co.icesi.auth.model.Submission;
import co.icesi.auth.repository.SubmissionRepository;
import co.icesi.auth.repository.ActivityRepository;
import co.icesi.auth.repository.UserRepository;
import co.icesi.auth.service.interfaces.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubmissionServiceImp implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Submission createSubmission(Submission submission, Long activityId, Long studentId) {
        submission.setActivity(activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found")));
        submission.setStudent(userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found")));
        return submissionRepository.save(submission);
    }

    @Override
    public Submission updateSubmission(Long id, Submission submissionDetails) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        submission.setContent(submissionDetails.getContent());
        return submissionRepository.save(submission);
    }

    @Override
    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }

    @Override
    public Submission getSubmission(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    @Override
    public List<Submission> getSubmissionsByActivity(Long activityId) {
        return submissionRepository.findByActivityId(activityId);
    }

    @Override
    public Submission gradeSubmission(Long id, Double grade, String feedback) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        submission.setGrade(grade);
        submission.setFeedback(feedback);
        return submissionRepository.save(submission);
    }
}
