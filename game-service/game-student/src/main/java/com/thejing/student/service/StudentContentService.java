package com.thejing.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentConditionDto;
import com.thejing.model.common.dtos.StudentUploadTableDto;
import com.thejing.model.common.pojos.GameManageList;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


public interface StudentContentService extends IService<GameManageList> {

    /**
     * 学生上传表单后保存
     * @param dto
     * @return
     */
    public ResponseResult uploadTable(StudentUploadTableDto dto, HttpServletRequest request);

    /**
     * 查询展示列表相关集合
     * @param dto
     * @return
     */
    public ResponseResult queryContentList(StudentConditionDto dto, HttpServletRequest request);

    /**
     * 删除相关信息
     * @param manageId
     * @return
     */
    ResponseResult deleteInformation(Integer manageId);

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPic(MultipartFile multipartFile);
}
