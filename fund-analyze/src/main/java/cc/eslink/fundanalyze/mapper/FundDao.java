package cc.eslink.fundanalyze.mapper;

import cc.eslink.fundanalyze.entity.Fund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * FundDao数据库操作接口类
 *
 * @author zyk
 */
@Repository
//@Mapper
public interface FundDao {

    int insert(@Param("pojo") Fund pojo);

    int insertList(@Param("pojos") List<Fund> pojos);

    int update(@Param("pojo") Fund pojo);

    List<String> queryByCodeList(@Param("fundCodeList") List<String> fundCodeList);

    List<String> queryCodeList();
}