package com.thejing.model.common.templates;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * easyexcel 导出工具所用 构建对象
 * @author LionLi
 */
@Data
public class EasyExcelModel{

    @ExcelProperty("项目号")
    private Long projectId;

    @ExcelProperty("申请人")
    private String stuName;

    @ExcelProperty("处理人")
    private String adUsername;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty("电话号码")
    @ColumnWidth(15)
    private Integer phone;

    @ExcelProperty("赛事类型")
    @ColumnWidth(15)
    private String type;

    @ExcelProperty("处理结果")
    @ColumnWidth(15)
    private String status;

    @ExcelProperty("日期")
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(15)
    private Date date;

}