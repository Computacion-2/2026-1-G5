package co.icesi.auth.dtos.activities;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivityDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private Double weight;
    private Long courseId;
}
