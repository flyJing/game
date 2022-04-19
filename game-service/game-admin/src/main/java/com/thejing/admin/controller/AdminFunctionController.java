package com.thejing.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.thejing.admin.listener.EasyExcelListener;
import com.thejing.admin.service.AdminFunctionService;
import com.thejing.model.common.dtos.AdminExportExcelDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameManageList;
import com.thejing.model.common.templates.EasyExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/admin/api/function")
@Slf4j
public class AdminFunctionController {

    @Autowired
    private AdminFunctionService adminFunctionService;

    /**
     * excel导入数据到数据库
     * @param multipartFile
     * @return
     */
    @PostMapping("/import")
    public ResponseResult importExcel(MultipartFile multipartFile){



////        String fileName = "C:\\Users\\Administrator\\Downloads\\test.xlsx";
        return adminFunctionService.importExcel(multipartFile);
    }

    /**
     * 将指定的数据导出到excel中
     * @param
     * @return
     */
    @PostMapping("/export")
    public void exportExcel(@RequestBody AdminExportExcelDto dto, HttpServletResponse response) throws IOException {
       adminFunctionService.exportExcel(dto,response);
    }
}
