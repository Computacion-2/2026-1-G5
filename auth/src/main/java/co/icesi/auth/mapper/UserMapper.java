package co.icesi.auth.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import co.icesi.auth.dtos.users.UserTeacherDTO;
import co.icesi.auth.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mappings({
        @Mapping(target = "name", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    })
    UserTeacherDTO teacherToDto(User user);

    @Named("userToString")
    default List<String> userToString(Set<User> users) {
        if (users == null) return new ArrayList<>();
        return users.stream().map(User::getUsername).collect(Collectors.toList());
    }
}
