package com.jay.shop.controllers;

import com.jay.shop.DTOs.FileDto;
import com.jay.shop.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping("/presigned-url")
    @ResponseBody
    ResponseEntity<FileDto.PresignedUrlResponse> getPresignedUrl(@RequestBody FileDto.PresignedUrlRequest request){
        var response = fileService.getPresignedUrl(request);
        //return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
