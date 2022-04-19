package com.thejing.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentLoginDto;
import com.thejing.model.common.pojos.GameStudent;

import javax.servlet.http.HttpServletRequest;

public interface StudentUserService extends IService<GameStudent> {

    /**
     * 用户登录的功能
     * @param dto
     * @return
     */
    public ResponseResult login(StudentLoginDto dto, HttpServletRequest request);

}
