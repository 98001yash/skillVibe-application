package com.company.skillVibe.media_service.controller;


import com.company.skillVibe.media_service.entities.MediaFile;
import com.company.skillVibe.media_service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file")MultipartFile file,
                                              @RequestParam("courseId") Long courseId) {
        try{
            String message = mediaService.uploadVideo(file, courseId);
            return ResponseEntity.ok(message);
        }catch(IOException e){
            return ResponseEntity.badRequest().body("Failed to upload file: "+e.getMessage());
        }
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<MediaFile> getVideo(@PathVariable Long courseId){
        return ResponseEntity.ok(mediaService.getVideoByCourse(courseId));
    }
}
