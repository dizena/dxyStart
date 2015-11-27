package com.aat.dxfy.base.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.User3job;
import com.aat.dxfy.base.dao.User3jobDao;
import com.aat.dxfy.base.service.User3jobService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户工作信息业务
 * @info java spring 
 * @update 无
 */
@Service
public class User3jobServiceImpl  implements User3jobService {

    @Autowired
    private User3jobDao user3jobDao;

    public int addBean(User3job bean){
        return user3jobDao.addBean(bean);
    }

    public int updateBean(User3job bean){
        return user3jobDao.updateBean(bean);
    }

    public int deleteBean(String id){
        return user3jobDao.deleteBean(id);
    }

    public User3job queryBean(String id){
        return user3jobDao.queryBean(id);
    }

    public List<User3job> queryAll(Map<String, Object> map){
        return user3jobDao.queryAll(map);
    }

    public long queryCount(Map<String, Object> map){
        return user3jobDao.queryCount(map);
    }

}
