package com.thejing.model.common.dtos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StudentConditionDto extends PageRequestDto{

    private Integer status;

    private Long projectId;
}
