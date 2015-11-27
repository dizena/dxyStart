package com.aat.dxfy.base.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import com.aat.dxfy.base.bean.User2edu;

@Repository
public interface User2eduDao{

    int addBean(User2edu bean);

    int updateBean(User2edu bean);

    int deleteBean(String id);

    User2edu queryBean(String id);

    List<User2edu> queryAll(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

}
