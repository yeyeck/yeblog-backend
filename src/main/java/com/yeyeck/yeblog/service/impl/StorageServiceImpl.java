package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.exception.StorageException;
import com.yeyeck.yeblog.exception.StorageFileNotFoundException;
import com.yeyeck.yeblog.service.IStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageServiceImpl implements IStorageService {

    private final Path rootLocation = Paths.get("/tmp/yeblog/image");

    private final Path headerLocation = Paths.get("/tmp/yeblog/system");

    private static String HEADER_URL = "/api/file/system/";

    public StorageServiceImpl() {
        init();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(headerLocation);
        } catch (IOException e) {
            throw new StorageException("Can not init storage", e );
        }
    }

    @Override
    public String store(MultipartFile file, Path path, String filename) {
        String originalFileName = file.getOriginalFilename();
        try {
            if (file.isEmpty()) {
                throw new StorageException("无法上传空文件： " + originalFileName);
            }
            if (originalFileName.contains("..")) {
                throw new StorageException("文件路径错误：  " + originalFileName);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        }
        catch (IOException e) {
            throw new StorageException("文件传输错误， " + originalFileName, e);
        }
    }

    @Override
    public String storeImages(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filename = uuid() + suffix;

        return store(file, this.rootLocation, filename);
    }

    @Override
    public Path load(Path path, String filename) {
        return path.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return loadResource(this.rootLocation, filename);
    }


    @Override
    public String storeHeader(MultipartFile file, String filename) {
        // 1. 存储图片
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        filename = filename + suffix;
        return HEADER_URL + store(file, this.headerLocation, filename);
    }

    @Override
    public Resource loadHeaderAsResource(String filename) {
        return loadResource(this.headerLocation, filename);
    }

    @Override
    public void deleteAll() {

    }

    private String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private Resource loadResource(Path location, String filename) {
        try {
            Path file = load(location, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }


}
