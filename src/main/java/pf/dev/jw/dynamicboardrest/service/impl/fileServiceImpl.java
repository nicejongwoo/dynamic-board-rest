package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pf.dev.jw.dynamicboardrest.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
public class fileServiceImpl implements FileService {

    @Value("${file.upload.directory}")
    private String uploadDirectory;
    @Override
    public void storeFile(MultipartFile file) {

        String originalFilename = file.getOriginalFilename(); //test.txt

        //원래 파일 명
        String originalName = FilenameUtils.getBaseName(originalFilename); //test
        //확장자
        String extension = FilenameUtils.getExtension(originalFilename); //txt
        //실제 저장 파일명(랜덤)
        UUID uuid = UUID.randomUUID();
        String newName = uuid.toString() + "_" + originalName;
        //사이즈
        long size = file.getSize();
        //파일타입
        String contentType = file.getContentType();

        //upload 경로 날짜별 생성
        Path path = makeDirectory(uploadDirectory);
        log.info("created_path:: " + path);

        //파일저장
        Path targetPath = path.resolve(newName).normalize(); //파일명이 포함된 전체 경로
        try {
            if (Files.exists(targetPath)) throw new IOException("중복된 파일이 이미 있습니다.");
            file.transferTo(targetPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static Path makeDirectory(String uploadDirectory) {

        LocalDate currentDate = LocalDate.now();

        String year = String.format("%04d", currentDate.getYear());
        String month = String.format("%02d", currentDate.getMonthValue());
        String day = String.format("%02d", currentDate.getDayOfMonth());

        log.info("yyyy:: " + year);
        log.info("mont:: " + month);
        log.info("day:: " + day);

        Path path = Paths.get(uploadDirectory, year, month, day);
        Path directories = null;
        try {
            directories = Files.createDirectories(path);
        } catch (IOException e) {
            log.error("e:: " + e.getMessage());
            e.printStackTrace();
        }

        return directories;
    }


}
