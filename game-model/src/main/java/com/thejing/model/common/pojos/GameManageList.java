package com.thejing.model.common.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("game_manage_list")
public class GameManageList {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("stu_id")
    private Integer stuId;

    @TableField("stu_name")
    private String stuName;

    @TableField("ad_id")
    private Integer adId;

    @TableField("ad_username")
    private String adUsername;

    @TableField("status")
    private Integer status;

    @TableField("type")
    private String type;

    @TableField("date")
    private Date date;

    @TableField("project_id")
    private Long projectId;

}
