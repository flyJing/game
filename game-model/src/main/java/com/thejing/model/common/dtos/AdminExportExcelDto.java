package com.thejing.model.common.dtos;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class AdminExportExcelDto {

    private Integer projectId;

    private String type;

    private String adUsername;

    private String stuName;

}
