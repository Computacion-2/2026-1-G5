package co.icesi.auth.service;

import co.icesi.auth.model.Submission;
import co.icesi.auth.repository.ActivityRepository;
import co.icesi.auth.repository.SubmissionRepository;
import co.icesi.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public List<Submission> getSubmissionsByActivityId(Long activityId) {
        return submissionRepository.findByActivityId(activityId);
    }

    public List<Submission> getSubmissionsByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public Submission createSubmission(Submission submission, Long activityId, Long studentId) {
        var activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        var student = userRepository.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        submission.setActivity(activity);
        submission.setStudent(student);
        submission.setSubmissionDate(LocalDateTime.now());

        return submissionRepository.save(submission);
    }

    public Submission gradeSubmission(Long id, Double grade, String feedback) {
        var submission = submissionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Entrega no encontrada"));

        submission.setGrade(grade);
        submission.setFeedback(feedback);
        submission.setGradedDate(LocalDateTime.now());

        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long id) {
        if (!submissionRepository.existsById(id)) {
            throw new IllegalArgumentException("Entrega no encontrada");
        }
        submissionRepository.deleteById(id);
    }
}
