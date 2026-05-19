package co.icesi.auth.repository;

import co.icesi.auth.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByActivityId(Long activityId);
    List<Submission> findByStudentId(Long studentId);
}
