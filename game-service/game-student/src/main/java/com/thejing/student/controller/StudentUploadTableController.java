package com.thejing.student.controller;

import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentUploadTableDto;
import com.thejing.student.service.StudentContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student/api/table")
public class StudentUploadTableController {

    @Autowired
    private StudentContentService studentContentService;



    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody StudentUploadTableDto dto, HttpServletRequest request){
        System.out.println(dto);
        return studentContentService.uploadTable(dto,request);
    }

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_pic")
    public ResponseResult uploadPic(MultipartFile multipartFile){

       return studentContentService.uploadPic(multipartFile);
    }


}
