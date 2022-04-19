package com.thejing.admin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.thejing.model.common.pojos.GameManageList;
import com.thejing.model.common.templates.EasyExcelModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EasyExcelListener extends AnalysisEventListener<EasyExcelModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EasyExcelModel.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10;
    List<EasyExcelModel> list = new ArrayList<EasyExcelModel>();


    /**
     * 每解析一条数据就会调用
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(EasyExcelModel data, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }

    }

    /**
     * 每个sheet的所有数据解析完成了 都会来调用这个方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
//        LOGGER.info("表头:"+);
    }

    /**
     * 将读取到的数据存储到数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//        //存储到数据库
//        LOGGER.info("存储数据库成功！");
    }
}
