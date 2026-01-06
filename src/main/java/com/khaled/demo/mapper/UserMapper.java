package com.khaled.demo.mapper;

import com.khaled.demo.model.dto.UserContactDto;
import com.khaled.demo.model.dto.UserDto;
import com.khaled.demo.model.dto.UserResponseDto;
import com.khaled.demo.model.entity.User;
import com.khaled.demo.model.entity.UserContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userContact", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "user", ignore = true)
    UserContact toEntity(UserContactDto dto);

    UserResponseDto toResponse(User user);
}
