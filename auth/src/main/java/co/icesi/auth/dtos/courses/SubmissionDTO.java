package co.icesi.auth.dtos.courses;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDTO {

    private Long id;

    @NotNull(message = "El ID de la actividad es requerido")
    private Long activityId;

    @NotNull(message = "El ID del estudiante es requerido")
    private Long studentId;

    @NotBlank(message = "El contenido de la entrega es requerido")
    private String content;

    private LocalDateTime submissionDate;

    private Double grade;

    private String feedback;

    private LocalDateTime gradedDate;
}
