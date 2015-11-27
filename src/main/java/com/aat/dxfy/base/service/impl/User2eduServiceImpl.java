package com.aat.dxfy.base.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.User2edu;
import com.aat.dxfy.base.dao.User2eduDao;
import com.aat.dxfy.base.service.User2eduService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户教育信息业务
 * @info java spring 
 * @update 无
 */
@Service
public class User2eduServiceImpl  implements User2eduService {

    @Autowired
    private User2eduDao user2eduDao;

    public int addBean(User2edu bean){
        return user2eduDao.addBean(bean);
    }

    public int updateBean(User2edu bean){
        return user2eduDao.updateBean(bean);
    }

    public int deleteBean(String id){
        return user2eduDao.deleteBean(id);
    }

    public User2edu queryBean(String id){
        return user2eduDao.queryBean(id);
    }

    public List<User2edu> queryAll(Map<String, Object> map){
        return user2eduDao.queryAll(map);
    }

    public long queryCount(Map<String, Object> map){
        return user2eduDao.queryCount(map);
    }

}
