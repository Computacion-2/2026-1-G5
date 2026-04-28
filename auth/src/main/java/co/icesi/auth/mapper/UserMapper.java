package co.icesi.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.users.UserTeacherDTO;
import co.icesi.auth.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target="name", source="user.firstName")
    public UserTeacherDTO teacherToDto(User user);
}
