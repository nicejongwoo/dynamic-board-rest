package pf.dev.jw.dynamicboardrest.service;

import pf.dev.jw.dynamicboardrest.controller.dto.request.CommentRequest;

public interface CommentService {
    Long register(CommentRequest request);

    void edit(Long id, CommentRequest request);

    void delete(Long id);

}
