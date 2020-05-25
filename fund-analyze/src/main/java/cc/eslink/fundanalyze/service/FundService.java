package cc.eslink.fundanalyze.service;

import cc.eslink.fundanalyze.entity.Fund;
import cc.eslink.fundanalyze.mapper.FundDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName FundService
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/24 0:42
 *@Version 1.0
 **/
@Service
public class FundService {

    @Autowired
    FundDao fundDao;

    public void save(Fund fund) {
        fundDao.insert(fund);
    }

    public void batchSave(List<Fund> list) {
        fundDao.insertList(list);
    }

    public List<String> queryByCodeList(List<String> fundCodeList) {
        return fundDao.queryByCodeList(fundCodeList);
    }

    public int update(Fund fund) {
        return fundDao.update(fund);
    }

    public List<String> queryCodeList() {
        return fundDao.queryCodeList();
    }
}
