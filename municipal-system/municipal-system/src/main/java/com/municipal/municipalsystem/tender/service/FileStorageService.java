package com.municipal.municipalsystem.tender.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads/";

    public String save(MultipartFile file) {

        try {

            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File destination = new File(uploadDir + fileName);

            file.transferTo(destination);

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }
}