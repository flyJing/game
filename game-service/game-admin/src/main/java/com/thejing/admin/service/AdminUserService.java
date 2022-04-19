package com.thejing.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.AdminLoginDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.pojos.GameAdmin;

import javax.servlet.http.HttpSession;

public interface AdminUserService extends IService<GameAdmin> {


    /**
     * 管理员登录的功能
     * @param dto
     * @return
     */
    public ResponseResult login(AdminLoginDto dto, HttpSession httpSession);

}
