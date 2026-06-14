package com.example.s3demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {


    private S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    public S3Service(S3Client s3Client){
        this.s3Client=s3Client;
    }


     public void uploadFile(MultipartFile file) throws IOException {
         String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
       s3Client.putObject(PutObjectRequest.builder()
                       .bucket(bucketName)
                       .key(fileName)
                       .build(),
               RequestBody.fromBytes(file.getBytes()));
     }

     public byte[] downloadFile(String key){
        ResponseBytes<GetObjectResponse> objectAsBytes=s3Client.getObjectAsBytes(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build()
        );
        return objectAsBytes.asByteArray();
     }

    public String deleteFile(String fileName) {

        DeleteObjectRequest deleteObjectRequest =
                DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build();

        s3Client.deleteObject(deleteObjectRequest);

        return "File deleted successfully: " + fileName;
    }

}
