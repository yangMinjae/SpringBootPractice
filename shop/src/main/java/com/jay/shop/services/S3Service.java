package com.jay.shop.services;

import com.jay.shop.DTOs.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final S3Presigner s3Presigner;
    private final S3Client s3Client;

    public String createPresignedUrl(String fileKey, String contentType){
        var putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileKey)
                .contentType(contentType)
                .build();

        var preSignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(putObjectRequest)
                .build();

        return s3Presigner.presignPutObject(preSignRequest).url().toString();
    }

    public HeadObjectResponse getObjectMetadata(String fileKey){
        try{
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileKey)
                    .build();
            return s3Client.headObject(headObjectRequest);
        }catch (NoSuchKeyException e){
            throw new IllegalStateException("S3에 해당 파일이 존재하지 않습니다 : " + fileKey, e);
        }
    }
}
