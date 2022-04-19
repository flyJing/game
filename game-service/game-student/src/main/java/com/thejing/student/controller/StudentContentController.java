package com.thejing.student.controller;


import com.thejing.model.common.dtos.AdminConditionDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentConditionDto;
import com.thejing.student.mapper.StudentUploadTableMapper;
import com.thejing.student.service.StudentContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student/api/content")
public class StudentContentController {

    @Autowired
    private StudentContentService studentContentService;

    /**
     * 学生分页查询信息列表
     * @param dto 分页参数和条件
     * @param
     * @return
     */
    @PostMapping("/pageQueryList")
    public ResponseResult pageQueryList(@RequestBody StudentConditionDto dto, HttpServletRequest request){
        System.out.println(dto);
        return studentContentService.queryContentList(dto,request);
    }

    @GetMapping("/deleteInformation/{manageId}")
    public ResponseResult deleteInformation(@PathVariable("manageId") Integer manageId){
        return studentContentService.deleteInformation(manageId);
    }

}
