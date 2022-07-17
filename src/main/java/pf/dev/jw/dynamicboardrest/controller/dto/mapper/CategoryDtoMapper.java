package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CategoryRequest;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.domain.Category;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    CategoryDtoMapper MAPPER = Mappers.getMapper(CategoryDtoMapper.class);

    @Mapping(source = "request.name", target = "name")
    @Mapping(source = "request.code", target = "code")
    @Mapping(source = "board", target = "board")
    Category toEntity(CategoryRequest request, Board board);

}
