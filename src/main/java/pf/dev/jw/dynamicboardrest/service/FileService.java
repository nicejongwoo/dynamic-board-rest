package pf.dev.jw.dynamicboardrest.service;

import org.springframework.web.multipart.MultipartFile;
import pf.dev.jw.dynamicboardrest.controller.dto.FileRequestDto;

public interface FileService {
    FileRequestDto storeFile(MultipartFile file);

    Long insertFile(FileRequestDto requestDto);
}
