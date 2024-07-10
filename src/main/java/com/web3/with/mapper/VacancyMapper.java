package com.web3.with.mapper;

import com.web3.with.entity.TagEntity;
import com.web3.with.entity.VacancyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyPreviewDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VacancyMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "salary", target = "salary"),
            @Mapping(source = "employer.companyName", target = "companyName", defaultValue = ""),
            @Mapping(source = "tagList", target = "tags", qualifiedByName = "mapTags")
    })
    VacancyPreviewDTO entityToDto(VacancyEntity entity);

    @Named("mapTags")
    default List<String> mapTags(Set<TagEntity> tags) {
        return tags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.toList());
    }

    default VacanciesRs pageToResponse(Page<VacancyEntity> page, boolean isLastPage) {
        List<VacancyPreviewDTO> dtoList = page.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        return new VacanciesRs(dtoList, isLastPage);
    }

}
