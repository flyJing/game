package com.thejing.admin;

import com.thejing.admin.mapper.AdminContentMapper;
import com.thejing.admin.mapper.AdminUserMapper;
import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameManageList;
import com.thejing.utils.common.IdsUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
public class SaveTest {

    @Autowired
    private AdminContentMapper adminContentMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Test
    public void save(){

//        for (int i = 0; i < 10; i++) {
//            GameManageList manageList = new GameManageList();
//            manageList.setDate(new Date());
//            manageList.setStatus(0);
//            manageList.setStuId(1);
//            manageList.setType("自然科学");
//            manageList.setStuName("小小军");
//            adminContentMapper.insert(manageList);
//        }

//        GameAdmin gameAdmin = new GameAdmin();
//        gameAdmin.setId(1);
//        gameAdmin.setJno(20190217001L);
//        gameAdmin.setUsername("涂老师");
//        gameAdmin.setPassword("123456");
//
//        adminUserMapper.insert(gameAdmin);

    }

}
