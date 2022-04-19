package com.thejing.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameStudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper extends BaseMapper<GameAdmin> {
}
