package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;
import pf.dev.jw.dynamicboardrest.domain.Article;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.domain.Category;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {

    ArticleDtoMapper MAPPER = Mappers.getMapper(ArticleDtoMapper.class);

    @Mapping(source = "board", target = "board")
    @Mapping(source = "category", target = "category")
    Article toEntity(ArticleRequest request, Board board, Category category);

}
