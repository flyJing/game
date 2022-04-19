package com.thejing.admin;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class EasyExcelTest {

    //E:\testExcel
    @Autowired
    DemoDataListener demoDataListener;

    @Test
    public void read(){

//        EasyExcel.w

        String fileName = "E:\\test.xls";
        EasyExcel.read(fileName,TestModel.class,demoDataListener).sheet().doRead();

    }

}
