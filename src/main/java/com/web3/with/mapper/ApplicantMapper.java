package com.web3.with.mapper;

import com.web3.with.entity.ApplicantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.openapitools.model.ApplicantDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicantMapper   {

   @Mappings({
           @Mapping(source = "email", target = "email"),
           @Mapping(source = "firstName", target = "firstName"),
           @Mapping(source = "secondName", target = "secondName"),
    })
    ApplicantDto entityToSimpleDto(ApplicantEntity entity);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "secondName", target = "secondName"),
    })
    ApplicantEntity simpleDtoToEntity(ApplicantDto dto);
}