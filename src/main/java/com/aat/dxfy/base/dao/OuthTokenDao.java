package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aat.dxfy.base.bean.OuthToken;

@Repository
public interface OuthTokenDao {
	int addBean(OuthToken token);

	int updateBean(OuthToken token);

	int deleteBean(String id);

	OuthToken queryBean(String id);
	
	OuthToken queryBeanCUID(Map<String, Object> map);

	List<OuthToken> queryAll(Map<String, Object> map);

	Long queryAllCount(Map<String, Object> map);
}
