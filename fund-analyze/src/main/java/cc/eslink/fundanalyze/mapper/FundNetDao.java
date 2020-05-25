package cc.eslink.fundanalyze.mapper;

import cc.eslink.fundanalyze.entity.FundNet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * FundNetDao数据库操作接口类
 *
 * @author zyk
 */
@Repository
public interface FundNetDao {

    int isExists(@Param("pojo") FundNet pojo);

    int insertList(@Param("pojos") List<FundNet> pojos);
}