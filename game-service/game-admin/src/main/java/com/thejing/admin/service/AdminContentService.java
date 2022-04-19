package com.thejing.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.AdminConditionDto;
import com.thejing.model.common.dtos.AdminUpdateDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.pojos.GameManageList;

import javax.servlet.http.HttpSession;

public interface AdminContentService extends IService<GameManageList> {

    /**
     * 管理员分页查询信息列表
     * @param dto
     * @return
     */
    public ResponseResult pageQueryList(AdminConditionDto dto);

    /**
     * 管理员同意审核的功能
     * @return
     * @param id
     */
    public ResponseResult agreeInformation(AdminUpdateDto id, HttpSession session);

    /**
     * 管理员拒绝审核的功能
     * @return
     */
    ResponseResult refuseInformation(AdminUpdateDto dto,HttpSession session);
}
