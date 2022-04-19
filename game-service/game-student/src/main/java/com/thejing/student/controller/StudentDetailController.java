package com.thejing.student.controller;


import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.student.service.StudentDetailService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/api/detail")
public class StudentDetailController {

    @Autowired
    private StudentDetailService studentDetailService;

    @GetMapping("/queryDetail/{manageId}")
    public ResponseResult queryDetail(@PathVariable("manageId") Integer manageId){
        return studentDetailService.queryDetail(manageId);
    }

}
