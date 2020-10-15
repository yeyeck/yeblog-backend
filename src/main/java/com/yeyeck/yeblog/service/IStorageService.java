package com.yeyeck.yeblog.service;

import org.springframework.core.io.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IStorageService {

    void init();

    String store(MultipartFile file, Path path, String filename);
    String storeImages(MultipartFile file);

    Path load(Path path, String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    String storeHeader(MultipartFile file, String fileName);

    Resource loadHeaderAsResource(String filename);
}
