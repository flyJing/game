package com.thejing.student.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentLoginDto;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameStudent;
import com.thejing.student.mapper.StudentUserMapper;
import com.thejing.student.service.StudentUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Transactional
@Service
@Slf4j
public class StudentUserServiceImpl extends ServiceImpl<StudentUserMapper, GameStudent> implements StudentUserService {

    /**
     * 用户登录的功能
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(StudentLoginDto dto, HttpServletRequest request) {
        //判断账号密码是否为空
        if (!dto.getSno().equals("") && StringUtils.isNoneBlank(dto.getPassword())){
            //查询账号
            GameStudent gameStudent = getOne(Wrappers.<GameStudent>lambdaQuery().eq(GameStudent::getSno, dto.getSno()));

            if (gameStudent == null){
                //账号为空
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户信息不存在");
            }
            //比对密码
            if (!dto.getPassword().equals(gameStudent.getPassword())){
                //密码错误
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            request.getSession().setAttribute("student",gameStudent);
            return ResponseResult.okResult("登录成功");

        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"请输入账号和密码");
        }
    }
}
