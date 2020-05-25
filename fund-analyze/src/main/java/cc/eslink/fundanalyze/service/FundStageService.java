package cc.eslink.fundanalyze.service;

import cc.eslink.fundanalyze.entity.FundStage;
import cc.eslink.fundanalyze.mapper.FundStageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName FundStageService
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 0:55
 *@Version 1.0
 **/
@Service
public class FundStageService {

    @Autowired
    FundStageDao fundStageDao;

    public int deleteCurData(String curDate, List<String> fundCodeList) {
        return fundStageDao.deleteCurData(curDate, fundCodeList);
    }

    public int batchSave(List<FundStage> stageList) {
        return fundStageDao.insertList(stageList);
    }
}
