package com.thejing.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thejing.admin.mapper.AdminContentDetailMapper;
import com.thejing.admin.mapper.AdminContentMapper;
import com.thejing.admin.service.AdminContentService;
import com.thejing.common.constants.ManageTypeConstant;
import com.thejing.model.common.dtos.AdminConditionDto;
import com.thejing.model.common.dtos.AdminUpdateDto;
import com.thejing.model.common.dtos.PageResponseResult;
import com.thejing.model.common.dtos.ResponseResult;
import com.thejing.model.common.enums.AppHttpCodeEnum;
import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameManageContent;
import com.thejing.model.common.pojos.GameManageList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@Transactional
public class AdminContentServiceImpl extends ServiceImpl<AdminContentMapper, GameManageList> implements AdminContentService {

    private final String from = "tj15676818429@qq.com";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AdminContentMapper adminContentMapper;

    @Autowired
    private AdminContentDetailMapper adminContentDetailMapper;

    /**
     * 管理员分页查询信息列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult pageQueryList(AdminConditionDto dto) {
        System.out.println(dto);
        //调整参数
        dto.checkParam();

        //分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<GameManageList> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        //按学生姓名查询
        if (StringUtils.isNoneBlank(dto.getStuName())){
            System.out.println(11);
            lambdaQueryWrapper.eq(GameManageList::getStuName,dto.getStuName());
        }

        //按处理的管理员姓名查询
        if (StringUtils.isNoneBlank(dto.getAdUsername())){
            System.out.println(22);
            lambdaQueryWrapper.eq(GameManageList::getAdUsername,dto.getAdUsername());
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

        //按照项目号
        if (dto.getProjectId() != null){
            lambdaQueryWrapper.eq(GameManageList::getProjectId,dto.getProjectId());
        }

        //按照比赛类型
        if (StringUtils.isNoneBlank(dto.getType())){
            lambdaQueryWrapper.eq(GameManageList::getType,dto.getType());
        }
        //按照时间倒序
        lambdaQueryWrapper.orderByDesc(GameManageList::getDate);

        page = page(page, lambdaQueryWrapper);

        //3.结果返回
        ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
        responseResult.ok(200,page.getRecords(),"查询成功");
//        responseResult.setData(page.getRecords());
        return responseResult;
    }

    /**
     * 管理员同意审核的功能
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult agreeInformation(AdminUpdateDto dto,HttpSession session) {
        if (dto != null){
            //修改状态为已通过
            dto.setStatus(ManageTypeConstant.PASS_THE_AUDIT);
            //从session中取出admin信息
            GameAdmin admin = (GameAdmin) session.getAttribute("user");
            if (admin == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            }
            adminContentMapper.updateByMangaId(dto,admin);
            //发送邮件通知
            GameManageContent gameManageContent = adminContentDetailMapper.selectOne(Wrappers.<GameManageContent>lambdaQuery().eq(GameManageContent::getManageId, dto.getManageId()));
            try {
                sendMail(from,gameManageContent.getEmail(),"审核结果","您提交的内容已通过审核,请上线查看");
            }catch (Exception e){
                log.error("邮件发送失败!");
            }finally {
                return ResponseResult.okResult("操作成功!");
            }
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
    }

    /**
     * 管理员拒绝审核的功能
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult refuseInformation(AdminUpdateDto dto,HttpSession session) {
        if (dto != null){
            //修改状态为未通过
            dto.setStatus(ManageTypeConstant.DIT_NOT_PASS);
            //从session中取出admin信息
            GameAdmin admin = (GameAdmin) session.getAttribute("user");
            if (admin == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            }
            adminContentMapper.updateByMangaId(dto,admin);
            //修改详细表中的拒绝理由
            adminContentDetailMapper.updateByManageId(dto);
            //发送邮件通知
            GameManageContent gameManageContent = adminContentDetailMapper.selectOne(Wrappers.<GameManageContent>lambdaQuery().eq(GameManageContent::getManageId, dto.getManageId()));
            try {
                sendMail(from,gameManageContent.getEmail(),"审核结果","您提交的内容未通过审核,请上线查看");
            }catch (Exception e){
                log.error("邮件发送失败!");
            }finally {
                return ResponseResult.okResult("操作成功!");
            }
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
    }

    /**
     * 发送邮件的方法
     */
    public void sendMail(String from,String to,String subject,String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from+"(阿姐)");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            System.out.println("邮件发送成功!");
    }


}
