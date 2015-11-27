package com.aat.dxfy.base.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.User4log;
import com.aat.dxfy.base.dao.User4logDao;
import com.aat.dxfy.base.service.User4logService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户日志信息业务
 * @info java spring 
 * @update 无
 */
@Service
public class User4logServiceImpl  implements User4logService {

    @Autowired
    private User4logDao user4logDao;

    public int addBean(User4log bean){
        return user4logDao.addBean(bean);
    }

    public int updateBean(User4log bean){
        return user4logDao.updateBean(bean);
    }

    public int deleteBean(String id){
        return user4logDao.deleteBean(id);
    }

    public User4log queryBean(String id){
        return user4logDao.queryBean(id);
    }

    public List<User4log> queryAll(Map<String, Object> map){
        return user4logDao.queryAll(map);
    }

    public long queryCount(Map<String, Object> map){
        return user4logDao.queryCount(map);
    }

}
