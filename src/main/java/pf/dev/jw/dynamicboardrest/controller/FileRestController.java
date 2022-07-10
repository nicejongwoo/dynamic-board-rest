package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pf.dev.jw.dynamicboardrest.controller.dto.FileRequestDto;
import pf.dev.jw.dynamicboardrest.service.FileService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FileRestController {

    private final FileService fileService;

    @PostMapping("/file/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        log.info("file:: " + file.getOriginalFilename());

        FileRequestDto requestDto = fileService.storeFile(file);

        Long savedId = fileService.insertFile(requestDto);

        return new ResponseEntity<>("success: " + savedId, HttpStatus.CREATED);
    }


}
