package com.thejing.model.common.dtos;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class AdminUpdateDto {

    private String reasonsNo;

    private Integer manageId;

    private Integer status;

}
