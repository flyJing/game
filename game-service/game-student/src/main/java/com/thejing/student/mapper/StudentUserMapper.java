package com.thejing.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thejing.model.common.pojos.GameStudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentUserMapper extends BaseMapper<GameStudent> {
}
