package com.web3.with.mapper;

import com.web3.with.entity.VacancyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyPreviewDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VacancyMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
//            @Mapping(source = "description", target = "description")
    })
    VacancyPreviewDTO entityToDto(VacancyEntity entity);

    default VacanciesRs pageToResponse(Page<VacancyEntity> page, boolean isLastPage) {
        List<VacancyPreviewDTO> dtoList = page.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        return new VacanciesRs(dtoList, isLastPage);
    }

}
