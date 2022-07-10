package pf.dev.jw.dynamicboardrest.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void storeFile(MultipartFile file);
}
