package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pf.dev.jw.dynamicboardrest.controller.dto.request.FileRequest;
import pf.dev.jw.dynamicboardrest.domain.File;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FileDtoMapperTest {

    @Test
    void toEntityTest() {
        FileRequest build = FileRequest.builder()
                .extension("pdf")
                .newName("abc")
                .originalName("test")
                .size(123123L)
                .type("text/pdf")
                .build();

        File file = FileDtoMapper.MAPPER.toEntity(build);

        assertThat(file).isNotNull();
        assertThat(file.getOriginalName()).isEqualTo(build.getOriginalName());
    }

}