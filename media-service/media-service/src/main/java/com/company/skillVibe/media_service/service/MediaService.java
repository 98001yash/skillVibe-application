package com.company.skillVibe.media_service.service;


import com.company.skillVibe.media_service.entities.MediaFile;
import com.company.skillVibe.media_service.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;

    public static String UPLOAD_DIR = "uploads/videos/";

    public String uploadVideo(MultipartFile file, Long courseId) throws IOException {
        if(file.isEmpty()){
            throw new RuntimeException("File is empty");
        }

        File directory = new File(UPLOAD_DIR);
        if(!directory.exists()){
            directory.mkdirs();
        }
        String filePath = UPLOAD_DIR = file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        MediaFile mediaFile = MediaFile.builder()
                .fileName(file.getOriginalFilename())
                .filePath(filePath)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .courseId(courseId)
                .build();
        mediaRepository.save(mediaFile);

        return "File uploaded successfully: "+ filePath;
    }

    public MediaFile getVideoByCourse(Long courseId){
        return mediaRepository.findByCourseId(courseId).orElseThrow(()->
                new RuntimeException("Video not found for course: "+courseId));
    }
}
