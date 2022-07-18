package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pf.dev.jw.dynamicboardrest.controller.dto.request.AnswerRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.AnswerResponse;
import pf.dev.jw.dynamicboardrest.domain.Answer;
import pf.dev.jw.dynamicboardrest.domain.Article;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerDtoMapper {

    AnswerDtoMapper MAPPER = Mappers.getMapper(AnswerDtoMapper.class);

    @Mapping(target = "article", source = "article")
    @Mapping(target = "content", source = "request.content")
    Answer toEntity(AnswerRequest request, Article article);

    List<AnswerResponse> toDtoList(List<Answer> answers);
}
