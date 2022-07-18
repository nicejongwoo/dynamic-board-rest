package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.AnswerDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.request.AnswerRequest;
import pf.dev.jw.dynamicboardrest.domain.Answer;
import pf.dev.jw.dynamicboardrest.domain.Article;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;
import pf.dev.jw.dynamicboardrest.repository.AnswerRepository;
import pf.dev.jw.dynamicboardrest.repository.ArticleRepository;
import pf.dev.jw.dynamicboardrest.service.AnswerService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Long register(AnswerRequest request) {
        Article article = checkArticle(request);
        Answer answer = AnswerDtoMapper.MAPPER.toEntity(request, article);
        answerRepository.save(answer);
        return answer.getId();
    }

    @Transactional
    @Override
    public void edit(Long id, AnswerRequest request) {
        Answer answer = checkAnswer(id);
        answer.editAnswerContent(request.getContent());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Answer answer = checkAnswer(id);
        answerRepository.delete(answer);
    }

    private Article checkArticle(AnswerRequest request) {
        return articleRepository.findById(request.getArticleId()).orElseThrow(() -> new CustomApiException("게시글이 없습니다.", HttpStatus.NOT_FOUND));
    }

    private Answer checkAnswer(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new CustomApiException("답변이 없습니다.", HttpStatus.NOT_FOUND));
    }
}
