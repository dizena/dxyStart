package com.aat.dxfy.base.service;

import java.util.*;
import com.aat.dxfy.base.bean.User4log;

public interface User4logService{

    int addBean(User4log bean);

    int updateBean(User4log bean);

    int deleteBean(String id);

    User4log queryBean(String id);

    List<User4log> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
