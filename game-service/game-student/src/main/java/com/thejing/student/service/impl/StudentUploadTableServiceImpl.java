package com.thejing.student.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.thejing.common.constants.ManageTypeConstant;
import com.thejing.model.common.dtos.PageResponseResult;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.dtos.StudentConditionDto;
import com.thejing.model.common.dtos.StudentUploadTableDto;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameManageContent;
import com.thejing.model.common.pojos.GameManageList;
import com.thejing.model.common.pojos.GameStudent;
import com.thejing.student.mapper.StudentContentMapper;
import com.thejing.student.mapper.StudentUploadTableMapper;
import com.thejing.student.service.StudentContentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class StudentUploadTableServiceImpl extends ServiceImpl<StudentUploadTableMapper, GameManageList> implements StudentContentService {

    @Autowired
    private StudentUploadTableMapper studentUploadTableMapper;

    @Autowired
    private StudentContentMapper studentContentMapper;

    /**
     * 学生上传表单后保存
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult uploadTable(StudentUploadTableDto dto, HttpServletRequest request) {
        try {
            GameManageList gameManageList = new GameManageList();
            //从session中取出用户信息
            GameStudent student = (GameStudent) request.getSession().getAttribute("student");
            gameManageList.setStuId(student.getId());
            gameManageList.setStuName(student.getUsername());
            gameManageList.setType(dto.getType());
            gameManageList.setDate(new Date());
            gameManageList.setProjectId(dto.getProjectId());
            //保存信息到list中
            studentUploadTableMapper.insert(gameManageList);
            //保存到Content中
            Integer manageId = gameManageList.getId();
            GameManageContent gameManageContent = new GameManageContent();
            if (dto.getImages() != null && dto.getImages().size() > 0){
                gameManageContent.setStaticUrl(StringUtils.join(dto.getImages(),","));
            }
            gameManageContent.setManageId(manageId);
            gameManageContent.setApplyMoney(dto.getApply_money());
            gameManageContent.setDescription(dto.getDescription());
            gameManageContent.setPhone(dto.getPhone());
            gameManageContent.setEmail(dto.getEmail());
            gameManageContent.setStuName(student.getUsername());
            gameManageContent.setPayType(dto.getPay_type());
            studentContentMapper.insert(gameManageContent);
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.UPLOAD_TABLE_ERROR);
        }
    }

    /**
     * 查询展示列表相关集合
     *
     * @param dto
     * @param request
     * @return
     */
    @Override
    public ResponseResult queryContentList(StudentConditionDto dto, HttpServletRequest request) {

        //调整参数
        dto.checkParam();

//        GameStudent student = (GameStudent) request.getSession().getAttribute("student");
//        //根据用户id查询
//        if (student != null){
//            //分页查询
//            IPage page = new Page(dto.getPage(), dto.getSize());
//            LambdaQueryWrapper<GameManageList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(GameManageList::getStuId,student.getId());
//            if (dto.getProjectId() != null){
//                lambdaQueryWrapper.eq(GameManageList::getProjectId,dto.getProjectId());
//            }
//            //按照当前状态查询
//            // 0:审核中  1: 审核成功  2: 未通过
//            if (dto.getStatus() != null){
//                if (dto.getStatus() == ManageTypeConstant.WAITING_FOR_REVIEW){
//                    lambdaQueryWrapper.eq(GameManageList::getStatus,ManageTypeConstant.WAITING_FOR_REVIEW);
//                }
//                if (dto.getStatus() == ManageTypeConstant.PASS_THE_AUDIT){
//                    lambdaQueryWrapper.eq(GameManageList::getStatus,ManageTypeConstant.PASS_THE_AUDIT);
//                }
//                if (dto.getStatus() == ManageTypeConstant.DIT_NOT_PASS){
//                    lambdaQueryWrapper.eq(GameManageList::getStatus,ManageTypeConstant.DIT_NOT_PASS);
//                }
//            }
//            //按照时间倒序
//            lambdaQueryWrapper.orderByDesc(GameManageList::getDate);
//
//            page = page(page, lambdaQueryWrapper);
//
//            ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(), (int) page.getTotal());
//            responseResult.ok(200,page.getRecords(),"查询成功");
//            return responseResult;
//        }else {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

            //分页查询
            IPage page = new Page(dto.getPage(), dto.getSize());
            LambdaQueryWrapper<GameManageList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(GameManageList::getStuId,1);
            if (dto.getProjectId() != null){
                lambdaQueryWrapper.eq(GameManageList::getProjectId,dto.getProjectId());
            }
            //按照当前状态查询
            // 0:审核中  1: 审核成功  2: 未通过
            if (dto.getStatus() != null){
                if (dto.getStatus() == ManageTypeConstant.WAITING_FOR_REVIEW){
                    lambdaQueryWrapper.eq(GameManageList::getStatus,ManageTypeConstant.WAITING_FOR_REVIEW);
                }
                if (dto.getStatus() == ManageTypeConstant.PASS_THE_AUDIT){
                    lambdaQueryWrapper.eq(GameManageList::getStatus,ManageTypeConstant.PASS_THE_AUDIT);
                }
                if (dto.getStatus() == ManageTypeConstant.DIT_NOT_PASS){
                    lambdaQueryWrapper.eq(GameManageList::getStatus,ManageTypeConstant.DIT_NOT_PASS);
                }
            }
            //按照时间倒序
            lambdaQueryWrapper.orderByDesc(GameManageList::getDate);

            page = page(page, lambdaQueryWrapper);

            ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(), (int) page.getTotal());
            responseResult.ok(200,page.getRecords(),"查询成功");
            return responseResult;

    }

    /**
     * 删除相关信息
     *
     * @param manageId
     * @return
     */
    @Override
    public ResponseResult deleteInformation(Integer manageId) {
        try {
            //查询list中的状态,如果为0就可以删除,如果不是就不能删除
            GameManageList gameManageList = studentUploadTableMapper.selectOne(Wrappers.<GameManageList>lambdaQuery().eq(GameManageList::getId, manageId));
            if (gameManageList.getStatus() == 0){

            }
            //删除list中的数据
            studentUploadTableMapper.deleteById(manageId);
            //删除详细表中的数据
            studentContentMapper.delete(Wrappers.<GameManageContent>lambdaQuery().eq(GameManageContent::getManageId,manageId));
            return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
        }catch (Exception e){
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

    /**
     * 图片上传
     *
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult uploadPic(MultipartFile multipartFile) {
//        //1.检查参数
//        if(multipartFile == null || multipartFile.getSize() == 0){
//            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
//        }
//        //2.上传图片到minIO中
//        String fileName = UUID.randomUUID().toString().replace("-", "");
//        //aa.jpg
//        String originalFilename = multipartFile.getOriginalFilename();
//        String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String fileId = null;
//        try {
//            fileId = fileStorageService.uploadImgFile("", fileName + postfix, multipartFile.getInputStream());
//            log.info("上传图片到MinIO中，fileId:{}",fileId);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("WmMaterialServiceImpl-上传文件失败");
//        }
//        return ResponseResult.okResult(fileId);
        return null;
    }
}
