package com.aat.dxfy.base.service;

import java.util.List;
import java.util.Map;

import com.aat.dxfy.base.bean.Urlauth;

public interface UrlauthService {
	int addUrlauth(Urlauth ua);

	int updateUrlauth(Urlauth ua);

	int deleteUrlauth(String id);

	Urlauth queryUrlauth(String id);

	List<Urlauth> queryAll(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);
}
