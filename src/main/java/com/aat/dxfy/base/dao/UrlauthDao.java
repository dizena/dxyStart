package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aat.dxfy.base.bean.Urlauth;

@Repository
public interface UrlauthDao {
	int addBean(Urlauth ua);

	int updateBean(Urlauth ua);

	int deleteBean(String id);

	Urlauth queryBean(String id);

	List<Urlauth> queryAll(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);
}
