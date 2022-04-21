package com.thejing.admin.controller;

import com.mysql.jdbc.log.Log;
import com.thejing.admin.service.AdminUserService;
import com.thejing.model.common.dtos.AdminLoginDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin/api/login")
@Slf4j
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;



    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody AdminLoginDto dto, HttpServletRequest request){
        HttpSession httpSession = request.getSession();

        return adminUserService.login(dto,httpSession);

    }

    @PostMapping("/findUsername")
    public ResponseResult findUsername(HttpServletRequest request){
        GameAdmin user = (GameAdmin) request.getSession().getAttribute("user");
        return ResponseResult.okResult(user.getUsername());
    }

    @GetMapping("/logOut")
    public ResponseResult logOut(HttpServletRequest request){
        //销毁回话
        request.getSession().invalidate();
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
