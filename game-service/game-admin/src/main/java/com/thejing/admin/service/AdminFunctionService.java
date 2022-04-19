package com.thejing.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.AdminExportExcelDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.pojos.GameManageList;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AdminFunctionService extends IService<GameManageList> {

    /**
     * 导出数据到excel
     *
     * @param dto
     * @param response
     * @return
     */
    public ResponseResult exportExcel(AdminExportExcelDto dto, HttpServletResponse response) throws IOException;

    /**
     * 从excel导入数据到数据库
     * @param multipartFile
     * @return
     */
    ResponseResult importExcel(MultipartFile multipartFile);
}
