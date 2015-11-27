package com.aat.dxfy.base.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import com.aat.dxfy.base.bean.User4log;

@Repository
public interface User4logDao{

    int addBean(User4log bean);

    int updateBean(User4log bean);

    int deleteBean(String id);

    User4log queryBean(String id);

    List<User4log> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
