package com.alex.mapper;

import com.alex.dto.FileUploadDto;
import com.alex.vo.FileUploadVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FileUploadMapper {
    FileUploadVo toVo(FileUploadDto fileUploadDto);
}
