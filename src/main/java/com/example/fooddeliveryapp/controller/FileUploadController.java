package com.example.fooddeliveryapp.controller;

import com.example.fooddeliveryapp.payload.response.ResponseData;
import com.example.fooddeliveryapp.payload.response.UploadFileResponse;
import com.example.fooddeliveryapp.services.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        ResponseData responseData = new ResponseData();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadFile/")
                .path(fileName)
                .toUriString();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(true);
        responseData.setData(new UploadFileResponse(
                fileName, fileDownloadUri, file.getContentType(), file.getSize()
        ));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        List<UploadFileResponse> uploadsList = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(true);
        responseData.setData(Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        ResponseData responseData = new ResponseData();
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        responseData.setStatus(HttpStatus.OK.value());
        responseData.setSuccess(true);
        responseData.setData(resource);
        responseData.setDesc(contentType);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
