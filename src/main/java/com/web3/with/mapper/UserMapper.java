package com.web3.with.mapper;

import com.web3.with.entity.RoleEntity;
import com.web3.with.entity.UserEntity;
import com.web3.with.security.model.RegistrationDto;
import com.web3.with.security.principal.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "authorities", qualifiedByName = "rolesM")
    })
    UserPrincipal entityToUserPrincipal(UserEntity userEntity);

    @Mappings({
            @Mapping(source = "username", target = "login"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
            @Mapping(target = "role", ignore = true)
    })
    UserEntity registrationDtoToUser(RegistrationDto registrationDto);

    @Named("rolesM")
    default List<? extends GrantedAuthority> mapAuthority(RoleEntity roleEntity) {
        return List.of(new SimpleGrantedAuthority(roleEntity.toString()));
    }

}
