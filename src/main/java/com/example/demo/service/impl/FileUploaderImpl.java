package com.example.demo.service.impl;

import com.example.demo.service.FileUploader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploaderImpl implements FileUploader{

    @Override
    public void upload(MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        Path path = Paths.get("images/" + file.getOriginalFilename());
        Files.write(path, bytes);
    }
    
}
