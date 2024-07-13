package com.web3.with.mapper;

import com.web3.with.entity.Role;
import com.web3.with.entity.User;
import com.web3.with.security.model.RegistrationDto;
import com.web3.with.security.principal.UserPrincipal;
import org.mapstruct.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "roles", target = "authorities", qualifiedByName = "rolesM")
    })
    UserPrincipal entityToUserPrincipal(User entity);

    @Mappings({
            @Mapping(source = "username", target = "login"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
    })
    User registrationDtoToUser(RegistrationDto registrationDto);


    @Named("rolesM")
    default List<? extends GrantedAuthority> mapAuthority(Set<Role> roles) {
        return roles.stream()
                .map(Role::getRole)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
