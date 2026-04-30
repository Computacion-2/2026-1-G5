package co.icesi.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.icesi.auth.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByActivityId(Long activityId);
    List<Submission> findByStudentId(Long studentId);
    Optional<Submission> findByActivityIdAndStudentId(Long activityId, Long studentId);
    List<Submission> findByStudentIdAndActivityCourseId(Long studentId, Long courseId);
}