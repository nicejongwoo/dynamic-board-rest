package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pf.dev.jw.dynamicboardrest.controller.dto.request.FileRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.FileResponse;
import pf.dev.jw.dynamicboardrest.service.FileService;

import javax.validation.constraints.Pattern;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FileRestController {

    private final FileService fileService;

    @PostMapping("/file/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        FileRequest requestDto = fileService.storeFile(file);
        Long id = fileService.insertFile(requestDto);
        return new ResponseEntity<>(CommonResponse.success(id, "파일이 저장되었습니다."), HttpStatus.CREATED);
    }

    @GetMapping("/attachment/{id}/file")
    public ResponseEntity<?> getFilesByAttachment(@PathVariable Long id) {
        List<FileResponse> files = fileService.getFilesByAttachment(id);
        return new ResponseEntity<>(CommonResponse.success(files, null), HttpStatus.OK);
    }

}
