package com.thejing.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thejing.model.common.dtos.AdminExportExcelDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.pojos.GameManageList;
import com.thejing.model.common.templates.EasyExcelModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminFunctionMapper extends BaseMapper<GameManageList> {

    /**
     * 导出数据到表格
     * @param dto
     * @return
     */
    public List<EasyExcelModel> exportExcel(@Param("dto") AdminExportExcelDto dto);

}
