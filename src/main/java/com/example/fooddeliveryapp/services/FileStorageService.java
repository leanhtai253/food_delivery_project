package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.uploads.FileStorageProperties;
import com.example.fooddeliveryapp.uploads.exception.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public String storeFile(MultipartFile file) throws FileStorageException;
    public Resource loadFileAsResource(String filename);
}
