package com.thejing.model.common.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
@TableName("game_manage_content")
public class GameManageContent {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("manage_id")
    private Integer manageId;

    @TableField("static_url")
    private String staticUrl;

    @TableField("apply_money")
    private String applyMoney;

    @TableField("description")
    private String description;

    @TableField("phone")
    private Integer phone;

    @TableField("pay_type")
    private String payType;

    @TableField("email")
    private String email;

    @TableField("reasons_no")
    private String reasonsNo;

    @TableField("stu_name")
    private String stuName;

    @TableField("status")
    private String status;

    @TableField("pic_list")
    private String[] picList;

}
