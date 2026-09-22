package com.jay.shop.services;

import com.jay.shop.DTOs.FileDto;
import com.jay.shop.entities.ImageFile;
import com.jay.shop.entities.Item;
import com.jay.shop.repositories.ImageFileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {
    private final S3Service s3Service;
    private final ImageFileRepository imageFileRepository;

    @Transactional
    public FileDto.PresignedUrlResponse getPresignedUrl(FileDto.PresignedUrlRequest request){
        String fileKey = "uploads/"+ UUID.randomUUID()+"-"+request.fileName();
        String presignedUrl = s3Service.createPresignedUrl(fileKey, request.contentType());

        ImageFile imageFile = ImageFile.builder().
                fileKey(fileKey)
                .originalName(request.fileName())
                .fileSize(request.fileSize())
                .contentType(request.contentType())
                .status(ImageFile.FileStatus.PENDING)
                .build();

        imageFileRepository.save(imageFile);
        return new FileDto.PresignedUrlResponse(presignedUrl, fileKey);
    }

    @Transactional
    public void confirmAndConnect(String fileKey, Item item){
        ImageFile imageFile = imageFileRepository.findByFileKey(fileKey)
                .orElseThrow(() -> new EntityNotFoundException("해당 이미지를 찾을 수 없습니다."));

        HeadObjectResponse meta = s3Service.getObjectMetadata(fileKey);
        imageFile.confirmUpload(meta.contentLength(), meta.contentType(), item);
    }
}
