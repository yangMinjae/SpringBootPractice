package com.jay.shop.DTOs;

public class FileDto {
    public record PresignedUrlRequest(String fileName, String contentType, Long fileSize) {}
    public record PresignedUrlResponse(String presignedUrl, String fileKey) {}
}
