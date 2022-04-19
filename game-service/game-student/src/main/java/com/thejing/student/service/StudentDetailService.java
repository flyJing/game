package com.thejing.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.pojos.GameManageContent;

public interface StudentDetailService extends IService<GameManageContent> {
    /**
     * 查询详细信息
     * @param manageId
     * @return
     */
    ResponseResult queryDetail(Integer manageId);
}
