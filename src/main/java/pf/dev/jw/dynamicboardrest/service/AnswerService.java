package pf.dev.jw.dynamicboardrest.service;

import pf.dev.jw.dynamicboardrest.controller.dto.request.AnswerRequest;

public interface AnswerService {
    Long register(AnswerRequest request);

    void edit(Long id, AnswerRequest request);

    void delete(Long id);
}
