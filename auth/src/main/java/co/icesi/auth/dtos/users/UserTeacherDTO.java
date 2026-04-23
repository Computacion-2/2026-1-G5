package co.icesi.auth.dtos.users;

import co.icesi.auth.model.User;
import lombok.Data;

@Data
public class UserTeacherDTO {
    private String name;
    private String email;
    public static UserTeacherDTO fromEntity(User teacher) {
        UserTeacherDTO dto = new UserTeacherDTO();
        dto.setName(teacher.getFirstName() + " "+ teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        return dto;
    }
}
