package com.aat.dxfy.base.service;

import java.util.*;
import com.aat.dxfy.base.bean.User3job;

public interface User3jobService{

    int addBean(User3job bean);

    int updateBean(User3job bean);

    int deleteBean(String id);

    User3job queryBean(String id);

    List<User3job> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
