package com.aat.dxfy.base.service;

import java.util.List;
import java.util.Map;

import com.aat.dxfy.base.bean.Ress;

public interface RessService {
	int addBean(Ress ress);

	int updateBean(Ress ress);

	int deleteBean(String id);

	Ress queryBean(String id);
	
	List<Ress> queryAll(Map<String, Object> map);
	
	List<Map<String, Object>> queryAllMap(Map<String, Object> map);
	
	long queryAllCount(Map<String, Object> map);
	
	Integer queryResSort(String resType,Integer resLevel,String resPid);
	
	List<Ress> queryParentRes(String resType,Integer resLevel);
	
	List<Ress> queryResByLP(String resPid,Integer resLevel);
}
