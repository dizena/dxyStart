package com.aat.dxfy.base.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import com.aat.dxfy.base.bean.User1info;

@Repository
public interface User1infoDao{

    int addBean(User1info bean);

    int updateBean(User1info bean);

    int deleteBean(String id);

    User1info queryBean(String id);

    List<User1info> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
