package cc.eslink.fundanalyze.service;

import cc.eslink.fundanalyze.entity.User;
import cc.eslink.fundanalyze.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@ClassName UserService
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/23 22:49
 *@Version 1.0
 **/
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User Sel(int id) {
        return userMapper.Sel(id);
    }
}
