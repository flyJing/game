package com.thejing.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thejing.model.common.dtos.AdminUpdateDto;
import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameManageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminContentMapper extends BaseMapper<GameManageList> {

    /**
     * 根据manageId修改当前信息状态为通过审核
     * @param dto
     * @param admin
     */
    void updateByMangaId(@Param("dto") AdminUpdateDto dto, @Param("admin") GameAdmin admin);

}
