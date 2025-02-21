package com.company.skillVibe.course_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "media-service")
public interface MediaClient {
    @PostMapping("/media/upload")
    String uploadVideo(@RequestParam("file") MultipartFile file, @RequestParam("courseId") Long courseId);
}
