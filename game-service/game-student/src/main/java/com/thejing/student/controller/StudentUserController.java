package com.thejing.student.controller;


import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentLoginDto;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameStudent;
import com.thejing.student.service.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/findUsername")
    public ResponseResult findUsername(HttpServletRequest request){
        GameStudent user = (GameStudent) request.getSession().getAttribute("student");
        return ResponseResult.okResult(user.getUsername());
    }

    @GetMapping("/logOut")
    public ResponseResult logOut(HttpServletRequest request){
        //销毁回话
        request.getSession().invalidate();
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
