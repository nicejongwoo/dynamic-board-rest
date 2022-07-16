package pf.dev.jw.dynamicboardrest.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardResponse {

    private Long id;
    private String code; //notice, faq, qna...
    private String name; //공지사항, faq, 질문과답변...
    private String skin; //basic, gallery, faq...
    private boolean secretEnable; //비밀글 사용여부
    private boolean attachmentEnable; //첨부 사용여부
    private boolean thumbnailEnable; //썸네일 사용여부
    private boolean categoryEnable; //카테고리 사용여부
    private boolean notificationEnable; //공지 사용여부
    private boolean commentEnable; //댓글 사용여부
    private boolean answerEnable; //답변 사용여부
    private boolean available; //게시판 사용여부

}
