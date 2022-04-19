package com.thejing.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thejing.model.common.pojos.GameManageContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDetailMapper extends BaseMapper<GameManageContent> {
}
