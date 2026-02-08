package com.khaled.demo.mapper;

import com.khaled.demo.model.dto.request.UserDto;
import com.khaled.demo.model.dto.respone.UserInfoDto;
import com.khaled.demo.model.dto.respone.UserResponseDto;
import com.khaled.demo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // For register / update (DTO -> Entity)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userContact", ignore = true)
    User toEntity(UserDto dto);

    UserResponseDto toResponse(User user);

    @Mapping(source = "birthDate", target = "dateOfBirth")
    UserInfoDto toInfoDto(User user);
}
