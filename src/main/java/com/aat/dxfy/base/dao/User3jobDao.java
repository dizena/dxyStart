package com.aat.dxfy.base.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import com.aat.dxfy.base.bean.User3job;

@Repository
public interface User3jobDao{

    int addBean(User3job bean);

    int updateBean(User3job bean);

    int deleteBean(String id);

    User3job queryBean(String id);

    List<User3job> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
