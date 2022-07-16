package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pf.dev.jw.dynamicboardrest.controller.dto.request.BoardRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardResponse;
import pf.dev.jw.dynamicboardrest.domain.Board;

@Mapper(componentModel = "spring")
public interface BoardDtoMapper {

    BoardDtoMapper MAPPER = Mappers.getMapper(BoardDtoMapper.class);

    Board toEntity(BoardRequest request);

    BoardResponse toDto(Board board);
}
