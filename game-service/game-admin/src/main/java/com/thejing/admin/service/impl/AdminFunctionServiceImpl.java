package com.thejing.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thejing.admin.listener.EasyExcelListener;
import com.thejing.admin.mapper.AdminFunctionMapper;
import com.thejing.admin.service.AdminFunctionService;
import com.thejing.model.common.dtos.AdminExportExcelDto;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameManageList;
import com.thejing.model.common.templates.EasyExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class AdminFunctionServiceImpl extends ServiceImpl<AdminFunctionMapper, GameManageList> implements AdminFunctionService {

    @Autowired
    private EasyExcelListener easyExcelListener;


    @Autowired
    private AdminFunctionMapper adminFunctionMapper;

    /**
     * 导出数据到excel
     *
     *
     * @param dto
     * @param response
     * @return
     */
    @Override
    public ResponseResult exportExcel(AdminExportExcelDto dto, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            List<EasyExcelModel> list = adminFunctionMapper.exportExcel(dto);
            for (EasyExcelModel easyExcelModel : list) {
                if(easyExcelModel.getStatus().equals("0")){
                    easyExcelModel.setStatus("未审核");
                }else if(easyExcelModel.getStatus().equals("1")){
                    easyExcelModel.setStatus("审核通过");
                }else {
                    easyExcelModel.setStatus("审核失败");
                }
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), EasyExcelModel.class).autoCloseStream(Boolean.FALSE).sheet("easyExcel")
                    .doWrite(list);
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
            return ResponseResult.okResult(AppHttpCodeEnum.DOWNLOAD_ERROR);
        }


    }

    /**
     * 从excel导入数据到数据库
     *
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult importExcel(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream,EasyExcelModel.class, easyExcelListener).sheet().doRead();




            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.UPLOAD_ERROR);
        }
    }
}
