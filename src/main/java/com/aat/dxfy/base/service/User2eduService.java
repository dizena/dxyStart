package com.aat.dxfy.base.service;

import java.util.*;
import com.aat.dxfy.base.bean.User2edu;

public interface User2eduService{

    int addBean(User2edu bean);

    int updateBean(User2edu bean);

    int deleteBean(String id);

    User2edu queryBean(String id);

    List<User2edu> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
