package co.icesi.auth.api;

import co.icesi.auth.dtos.submissions.SubmissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public interface SubmissionApi {

    @PostMapping
    @PreAuthorize("hasAuthority('SUBMISSION_CREATE')")
    public ResponseEntity<SubmissionDTO> createSubmission(@RequestBody SubmissionDTO submissionDTO);

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBMISSION_UPDATE')")
    public ResponseEntity<SubmissionDTO> updateSubmission(@PathVariable Long id, @RequestBody SubmissionDTO submissionDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBMISSION_DELETE')")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBMISSION_READ')")
    public ResponseEntity<SubmissionDTO> getSubmission(@PathVariable Long id);

    @GetMapping("/activity/{activityId}")
    @PreAuthorize("hasAuthority('SUBMISSION_READ')")
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByActivity(@PathVariable Long activityId);

    @PatchMapping("/{id}/grade")
    @PreAuthorize("hasAuthority('SUBMISSION_GRADE')")
    public ResponseEntity<SubmissionDTO> gradeSubmission(@PathVariable Long id, @RequestParam Double grade, @RequestParam String feedback);
}
