package com.example.demo.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileUploader {
    public void upload(MultipartFile file) throws Exception;
}
