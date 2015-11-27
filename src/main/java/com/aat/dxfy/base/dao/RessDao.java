package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aat.dxfy.base.bean.Ress;

@Repository
public interface RessDao {
	int addBean(Ress ress);

	int updateBean(Ress ress);

	int deleteBean(String id);

	Ress queryBean(String id);

	List<Ress> queryAll(Map<String, Object> map);

	List<Map<String, Object>> queryAllMap(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);

	Integer queryResSort(Map<String, Object> map);

	List<Ress> queryParentRes(Map<String, Object> map);

	List<Ress> queryResByLP(Map<String, Object> map);

}
