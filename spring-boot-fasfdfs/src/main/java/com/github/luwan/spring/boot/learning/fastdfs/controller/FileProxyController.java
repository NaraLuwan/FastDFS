package com.github.luwan.spring.boot.learning.fastdfs.controller;

import com.github.luwan.spring.boot.learning.fastdfs.constants.ResponseCodeMsg;
import com.github.luwan.spring.boot.learning.fastdfs.constants.ResponseData;
import com.github.luwan.spring.boot.learning.fastdfs.exception.FastDFSException;
import com.github.luwan.spring.boot.learning.fastdfs.service.FastDFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author luwan
 * @date 2020/1/10
 */
@RestController
@RequestMapping("/file")
public class FileProxyController {

    @Autowired
    private FastDFSService fastDFSService;

    @PostMapping(value = "/upload")
    public ResponseData upload(@RequestParam(name = "file") MultipartFile file) throws IOException, FastDFSException {
        if (file == null) {
            return ResponseData.fail(ResponseCodeMsg.FILE_NOT_EXISTS);
        }
        try {
            return ResponseData.success(fastDFSService.upload(file.getOriginalFilename(), file.getInputStream()));
        } catch (Throwable e) {
            throw e;
        }
    }
}
