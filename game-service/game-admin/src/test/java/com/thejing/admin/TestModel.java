package com.thejing.admin;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TestModel {

    @ExcelProperty("学生姓名")
    private String username;

    @ExcelProperty("学生性别")
    private String sex;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("出生日期")
    private Date date;

}
