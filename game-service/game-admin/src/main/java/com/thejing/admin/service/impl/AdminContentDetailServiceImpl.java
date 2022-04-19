package com.thejing.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thejing.admin.mapper.AdminContentDetailMapper;
import com.thejing.admin.service.AdminContentDetailService;
import com.thejing.model.common.dtos.PageResponseResult;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameManageContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Transactional
@Slf4j
public class AdminContentDetailServiceImpl extends ServiceImpl<AdminContentDetailMapper, GameManageContent> implements AdminContentDetailService {

    @Autowired
    private AdminContentDetailMapper adminContentDetailMapper;


    /**
     * 根据id查询数据详情
     *
     * @param manageId
     * @return
     */
    @Override
    public ResponseResult queryDetail(Integer manageId) {

        if (manageId != null){
            GameManageContent gameManageContent = getOne(Wrappers.<GameManageContent>lambdaQuery().eq(GameManageContent::getManageId, manageId));
            if (gameManageContent == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
            }
            //图片分割
            String staticUrl = gameManageContent.getStaticUrl();
            String[] split = staticUrl.split(",");
            gameManageContent.setPicList(split);

            return ResponseResult.okResult(gameManageContent);
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
    }


}
