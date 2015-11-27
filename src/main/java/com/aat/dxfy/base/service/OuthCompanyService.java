package com.aat.dxfy.base.service;

import java.util.List;
import java.util.Map;

import com.aat.dxfy.base.bean.OuthCompany;

public interface OuthCompanyService {
	int addBean(OuthCompany company);

	int updateBean(OuthCompany company);

	int deleteBean(String id);

	OuthCompany queryBean(String id);

	List<OuthCompany> queryAll(Map<String, Object> map);

	Long queryAllCount(Map<String, Object> map);

	int addOuthCompany(String resposeUrl, String eename,
			String eelogo);

}
