package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.service.IStorageService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    private IStorageService storageService;



    @Autowired
    public void setStorageService( IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("")
    @RequiresAuthentication
    public String uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> list = multipartHttpServletRequest.getFiles("file");
        return storageService.storeImages(list.get(0));
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("header")
    @RequiresAuthentication
    public String uploadHeader(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> list = multipartHttpServletRequest.getFiles("file");
        return storageService.storeHeader(list.get(0), YeConstants.IMAGE_HEADER);
    }

    @PostMapping("logo")
    @RequiresAuthentication
    public String uploaderLogo(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> list = multipartHttpServletRequest.getFiles("file");
        return storageService.storeHeader(list.get(0), YeConstants.IMAGE_LOGO);
    }

    @GetMapping("/system/{filename:.+}")
    public ResponseEntity<Resource> serveHeader(@PathVariable String filename) {
        Resource file = storageService.loadHeaderAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
