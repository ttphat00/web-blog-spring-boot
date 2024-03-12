package com.example.webblog.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    void save(MultipartFile file);
    Resource load(String filename);
    String buildFileUrl(MultipartFile file);
}
