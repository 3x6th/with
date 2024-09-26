package com.web3.with.mapper;

import com.web3.with.entity.ResumeEntity;
import com.web3.with.entity.TagEntity;
import org.mapstruct.*;
import org.openapitools.model.ResumeDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResumeMapper {
    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "desiredSalary", target = "desiredSalary"),
            @Mapping(source = "workMode", target = "workMode"),
            @Mapping(source = "location", target = "location"),
    })
    ResumeDto entityToSimpleDto(ResumeEntity entity);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "desiredSalary", target = "desiredSalary"),
            @Mapping(source = "workMode", target = "workMode"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "tags", target = "tagList", qualifiedByName = "string"),
    })
    ResumeEntity simpleDtoToEntity(ResumeDto dto);

    @Named("string")
    default Set<TagEntity> stringToTag(List<String> strings) {
        Set<TagEntity> tags = new HashSet<TagEntity>();
        for (String string : strings) {
            TagEntity tag = new TagEntity();
            String tagName = string.substring(0, 1).toUpperCase() + string.substring(1);
            tag.setName(tagName);
            tags.add(tag);
        }
        return tags;
    }
}
