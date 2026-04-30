package co.icesi.auth.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import co.icesi.auth.dtos.users.UserTeacherDTO;
import co.icesi.auth.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target="name", source="user.firstName")
    public UserTeacherDTO teacherToDto(User user);

    @Named("userToString")
    public default List<String> userToString(Set<User> users){
        
        List<String> tmp = new ArrayList<>();
        if (users == null) return tmp;
        
        for (User elem : users) {
            tmp.add(elem.getUsername());
        }
        return tmp;
    }
}
