package com.web3.with.mapper;

import com.web3.with.entity.EmployerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.openapitools.model.EmployerDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployerMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(source = "website", target = "website"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "email", target = "email"),
            @Mapping(target = "vacancies", source = "vacancies")
    })
    EmployerDTO entityToSimpleDto(EmployerEntity entity);

}
