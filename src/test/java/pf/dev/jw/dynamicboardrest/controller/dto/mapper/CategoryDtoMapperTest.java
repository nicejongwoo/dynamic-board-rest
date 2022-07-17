package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CategoryRequest;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.domain.Category;

import static org.assertj.core.api.Assertions.*;

class CategoryDtoMapperTest {

    @Test
    void toEntityTest() {
        CategoryRequest build = CategoryRequest.builder()
                .code("001")
                .name("CATEGORY_001")
                .boardId(null)
                .build();

        Category category = CategoryDtoMapper.MAPPER.toEntity(build, null);

        assertThat(category).isNotNull();
        assertThat(category.getCode()).isEqualTo(build.getCode());
    }

}