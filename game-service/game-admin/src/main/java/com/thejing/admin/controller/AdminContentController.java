package com.thejing.admin.controller;

import com.thejing.admin.service.AdminContentService;
import com.thejing.model.common.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin/api/content")
public class AdminContentController {

    @Autowired
    private AdminContentService adminContentService;

    /**
     * 管理员分页查询信息列表
     * @param dto 分页参数和条件
     * @param
     * @return
     */
    @PostMapping("/pageQueryList")
    public ResponseResult pageQueryList(@RequestBody AdminConditionDto dto){

        return adminContentService.pageQueryList(dto);

    }

    /**
     * 管理员同意审核的功能
     * @return
     */
    @PostMapping("/agreeInformation")
    public ResponseResult agreeInformation(@RequestBody AdminUpdateDto dto, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        return adminContentService.agreeInformation(dto,httpSession);
    }

    /**
     * 管理员拒绝审核的功能
     * @return
     */
    @PostMapping("/refuseInformation")
    public ResponseResult refuseInformation(@RequestBody AdminUpdateDto dto, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        return adminContentService.refuseInformation(dto,httpSession);
    }


}
