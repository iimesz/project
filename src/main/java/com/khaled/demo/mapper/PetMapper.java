package com.khaled.demo.mapper;

import com.khaled.demo.model.dto.request.PetDto;
import com.khaled.demo.model.dto.respone.PetInfoDto;
import com.khaled.demo.model.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Pet toEntity(PetDto dto);

    void updatePetFromDto(PetDto dto, @MappingTarget Pet pet);

    PetInfoDto toInfoDto(Pet pet);
}
