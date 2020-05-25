package cc.eslink.fundanalyze.service;

import cc.eslink.fundanalyze.entity.FundNet;
import cc.eslink.fundanalyze.mapper.FundNetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName FundNetService
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 2:08
 *@Version 1.0
 **/
@Service
public class FundNetService {

    @Autowired
    FundNetDao fundNetDao;

    public boolean isExists(FundNet fundNet) {
        return fundNetDao.isExists(fundNet) > 0;
    }

    public int batchSave(List<FundNet> list) {
        return fundNetDao.insertList(list);
    }
}
