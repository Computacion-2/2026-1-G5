package co.icesi.auth.dtos.submissions;

import lombok.Data;

@Data
public class SubmissionDTO {
    private Long id;
    private String content;
    private Double grade;
    private String feedback;
    private Long activityId;
    private Long studentId;
}
