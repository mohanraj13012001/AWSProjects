package com.example.s3demo.controllers;


import com.example.s3demo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {

    private S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service){
        this.s3Service=s3Service;
    }

    @PostMapping("upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        s3Service.uploadFile(multipartFile);
        return ResponseEntity.ok("File Uploaded SucessFully");
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName){
        byte[] bytes=s3Service.downloadFile(fileName);
        return ResponseEntity.ok(bytes);
    }

}
