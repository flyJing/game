package com.thejing.student.controller;


import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentLoginDto;
import com.thejing.student.service.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student/api/login")
public class StudentUserController {

    @Autowired
    private StudentUserService studentUserService;


    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody StudentLoginDto dto, HttpServletRequest request){
        return studentUserService.login(dto,request);
    }

}
