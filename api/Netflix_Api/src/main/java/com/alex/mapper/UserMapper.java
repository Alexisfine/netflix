package com.alex.mapper;

import com.alex.dto.UserDto;
import com.alex.dto.UserRegisterDto;
import com.alex.dto.UserUpdateDto;
import com.alex.model.User;
import com.alex.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserRegisterDto userCreateDto);

    User updateEntity(@MappingTarget User user, UserUpdateDto userUpdateDto);

}
