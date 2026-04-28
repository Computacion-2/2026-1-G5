package co.icesi.auth.dtos.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {

    private Long id;

    @NotBlank(message = "El nombre del curso es requerido")
    private String name;

    @NotBlank(message = "La descripción del curso es requerida")
    private String description;

    @NotBlank(message = "El código del curso es requerido")
    private String code;

    @NotNull(message = "Los créditos del curso son requeridos")
    @Positive(message = "Los créditos deben ser un número positivo")
    private Integer credits;

    private Long teacherId;
}
