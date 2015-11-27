package com.aat.dxfy.base.service;

import java.util.*;
import com.aat.dxfy.base.bean.User1info;

public interface User1infoService{

    int addBean(User1info bean);

    int updateBean(User1info bean);

    int deleteBean(String id);

    User1info queryBean(String id);

    List<User1info> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
