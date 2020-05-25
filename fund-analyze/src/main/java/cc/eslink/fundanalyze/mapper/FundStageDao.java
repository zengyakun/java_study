package cc.eslink.fundanalyze.mapper;

import cc.eslink.fundanalyze.entity.FundStage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * FundStageDao数据库操作接口类
 *
 * @author zyk
 */
@Repository
public interface FundStageDao {

    int insertList(@Param("pojos") List<FundStage> pojos);

    int deleteCurData(@Param("curDate") String curDate, @Param("fundCodeList") List<String> fundCodeList);
}