package com.thejing.model.common.dtos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AdminConditionDto extends PageRequestDto{
    @TableField("stu_id")
    private Integer stuId;

    @TableField("stu_name")
    private String stuName;

    @TableField("ad_username")
    private String adUsername;

    @TableField("status")
    private Integer status;

    @TableField("type")
    private String type;

    @TableField("date")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private Date date;

    @TableField("project_id")
    private Long projectId;
}
