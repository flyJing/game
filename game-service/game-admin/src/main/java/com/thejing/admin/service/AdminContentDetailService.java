package com.thejing.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.pojos.GameManageContent;

public interface AdminContentDetailService extends IService<GameManageContent> {

    /**
     * 根据id查询数据详情
     * @param id
     * @return
     */
    public ResponseResult queryDetail(Integer id);


}
