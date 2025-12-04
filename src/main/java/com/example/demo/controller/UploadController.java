package com.example.demo.controller;

import com.example.demo.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    /*public Result upload(String name, Integer age , File  file) throws IOException {

        log.info("接收参数{},{},{}",name,age,file);
        //获取原始文件名
        String originalFilename = file.getName();

        //新的文件名
        String newFileName = UUID.randomUUID().toString() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存文件
        file.renameTo(new File("D:/" + newFileName));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接收参数{}",file);
        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = aliyunOSSOperator.upload(file.getBytes(), newFileName);
        return Result.success(url);
    }
}
