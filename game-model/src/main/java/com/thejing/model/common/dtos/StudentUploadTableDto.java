package com.thejing.model.common.dtos;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class StudentUploadTableDto {

    private Long projectId;

    private Integer stuId;

    private String stuName;

    private String type;

    private Integer phone;

    private String email;

    private String apply_money;

    private String pay_type;

    private String description;

    private List<String> images;

}
