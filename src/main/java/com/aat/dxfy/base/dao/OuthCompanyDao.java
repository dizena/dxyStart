package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aat.dxfy.base.bean.OuthCompany;

@Repository
public interface OuthCompanyDao {
	int addBean(OuthCompany company);

	int updateBean(OuthCompany company);

	int deleteBean(String id);

	OuthCompany queryBean(String id);

	List<OuthCompany> queryAll(Map<String, Object> map);

	Long queryAllCount(Map<String, Object> map);

}
