package com.thejing.student.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameManageContent;
import com.thejing.student.mapper.StudentDetailMapper;
import com.thejing.student.service.StudentDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class StudentDetailServiceImpl extends ServiceImpl<StudentDetailMapper, GameManageContent> implements StudentDetailService {
    /**
     * 查询详细信息
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
