package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pf.dev.jw.dynamicboardrest.controller.dto.request.FileRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.FileResponse;
import pf.dev.jw.dynamicboardrest.domain.File;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileDtoMapper {

    FileDtoMapper MAPPER = Mappers.getMapper(FileDtoMapper.class);

    File toEntity(FileRequest request);

    List<FileResponse> toDtoList(List<File> files);

}
