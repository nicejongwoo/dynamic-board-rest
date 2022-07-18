package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CommentRequest;
import pf.dev.jw.dynamicboardrest.domain.Answer;
import pf.dev.jw.dynamicboardrest.domain.Comment;

@Mapper(componentModel = "spring")
public interface CommentDtoMapper {

    CommentDtoMapper MAPPER = Mappers.getMapper(CommentDtoMapper.class);

    @Mapping(target = "answer", source = "answer")
    @Mapping(target = "content", source = "request.content")
    Comment toEntity(CommentRequest request, Answer answer);

}
