package pf.dev.jw.dynamicboardrest.service;

import org.springframework.web.multipart.MultipartFile;
import pf.dev.jw.dynamicboardrest.controller.dto.request.FileRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.FileResponse;

import java.util.List;

public interface FileService {
    FileRequest storeFile(MultipartFile file);

    Long insertFile(FileRequest request);

    List<FileResponse> getFilesByAttachment(Long id);

}
