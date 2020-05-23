package cc.eslink.fundanalyze.mapper;

import cc.eslink.fundanalyze.entity.User;
import org.springframework.stereotype.Repository;

/**
 *@ClassName UserMapper
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/23 22:49
 *@Version 1.0
 **/
@Repository
public interface UserMapper {

    User Sel(int id);
}
