package com.aat.dxfy.base.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.User1info;
import com.aat.dxfy.base.dao.User1infoDao;
import com.aat.dxfy.base.service.User1infoService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户基本信息业务
 * @info java spring 
 * @update 无
 */
@Service
public class User1infoServiceImpl  implements User1infoService {

    @Autowired
    private User1infoDao user1infoDao;

    public int addBean(User1info bean){
        return user1infoDao.addBean(bean);
    }

    public int updateBean(User1info bean){
        return user1infoDao.updateBean(bean);
    }

    public int deleteBean(String id){
        return user1infoDao.deleteBean(id);
    }

    public User1info queryBean(String id){
        return user1infoDao.queryBean(id);
    }

    public List<User1info> queryAll(Map<String, Object> map){
        return user1infoDao.queryAll(map);
    }

    public long queryCount(Map<String, Object> map){
        return user1infoDao.queryCount(map);
    }

}
