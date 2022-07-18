package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.CommentDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CommentRequest;
import pf.dev.jw.dynamicboardrest.domain.Answer;
import pf.dev.jw.dynamicboardrest.domain.Comment;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;
import pf.dev.jw.dynamicboardrest.repository.AnswerRepository;
import pf.dev.jw.dynamicboardrest.repository.CommentRepository;
import pf.dev.jw.dynamicboardrest.service.CommentService;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    @Override
    public Long register(CommentRequest request) {
        Answer answer = checkAnswer(request);
        Comment comment = CommentDtoMapper.MAPPER.toEntity(request, answer);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    public void edit(Long id, CommentRequest request) {
        Comment comment = checkComment(id);
        comment.editContent(request.getContent());
    }

    @Override
    public void delete(Long id) {
        Comment comment = checkComment(id);
        commentRepository.delete(comment);
    }

    private Answer checkAnswer(CommentRequest request) {
        return answerRepository.findById(request.getAnswerId()).orElseThrow(() -> new CustomApiException("답변이 없습니다.", HttpStatus.NOT_FOUND));
    }

    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CustomApiException("댓글이 없습니다.", HttpStatus.NOT_FOUND));
    }
}
