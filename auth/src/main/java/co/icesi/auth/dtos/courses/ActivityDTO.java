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
public class ActivityDTO {

    private Long id;

    @NotBlank(message = "El título de la actividad es requerido")
    private String title;

    private String description;

    @NotNull(message = "La fecha de entrega es requerida")
    private LocalDateTime dueDate;

    @NotNull(message = "El ID del curso es requerido")
    private Long courseId;
}
