package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pf.dev.jw.dynamicboardrest.controller.dto.request.BoardRequest;
import pf.dev.jw.dynamicboardrest.domain.Board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BoardDtoMapperTest {

    @Test
    void toEntityTest() {
        BoardRequest build = BoardRequest.builder()
                .code("notice")
                .name("공지사항")
                .skin("basic")
                .available(true)
                .attachmentEnable(true)
                .answerEnable(false)
                .categoryEnable(true)
                .commentEnable(false)
                .notificationEnable(true)
                .secretEnable(false)
                .thumbnailEnable(false)
                .build();

        Board board = BoardDtoMapper.MAPPER.toEntity(build);

        assertThat(board).isNotNull();
        assertThat(board.getCode()).isEqualTo(build.getCode());

    }

}