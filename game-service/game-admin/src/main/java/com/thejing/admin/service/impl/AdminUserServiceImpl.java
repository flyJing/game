package com.thejing.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thejing.admin.mapper.AdminUserMapper;
import com.thejing.admin.service.AdminUserService;
import com.thejing.model.common.dtos.AdminLoginDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
@Slf4j
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, GameAdmin> implements AdminUserService {



    /**
     * 管理员登录的功能
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(AdminLoginDto dto,HttpSession httpSession) {
        //判断账号密码是否为空
        if (!dto.getJon().equals("") && StringUtils.isNoneBlank(dto.getPassword())){
            //查询账号
            GameAdmin dbAdmin = getOne(Wrappers.<GameAdmin>lambdaQuery().eq(GameAdmin::getJno, dto.getJon()));

            if (dbAdmin == null){
                //账号为空
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户信息不存在");
            }
            //比对密码
            if (!dto.getPassword().equals(dbAdmin.getPassword())){
                //密码错误
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            httpSession.setAttribute("user",dbAdmin);
            return ResponseResult.okResult("登录成功");

        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"请输入账号和密码");
        }
    }
}
