package com.thejing.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thejing.model.common.dtos.AdminUpdateDto;
import com.thejing.model.common.pojos.GameManageContent;
import com.thejing.model.common.pojos.GameManageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminContentDetailMapper extends BaseMapper<GameManageContent> {

    /**
     * 根绝id查询
     * @param manageId
     */
    void queryById(Integer manageId);

    /**
     * 修改拒绝理由
     * @param dto
     */
    void updateByManageId(@Param("dto") AdminUpdateDto dto);
}
