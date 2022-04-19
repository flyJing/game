package com.thejing.admin.controller;

import com.thejing.admin.service.AdminContentDetailService;
import com.thejing.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/detail")
public class AdminContentDetailController {

    @Autowired
    private AdminContentDetailService adminContentDetailService;

    /**
     * 根绝id查询详细信息的功能
     * @param id
     * @return
     */
    @GetMapping("/queryDetail/{id}")
    public ResponseResult queryDetail(@PathVariable("id") Integer id){
        return adminContentDetailService.queryDetail(id);
    }



}
