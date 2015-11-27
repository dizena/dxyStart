package com.aat.dxfy.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.OuthCompany;
import com.aat.dxfy.base.dao.OuthCompanyDao;
import com.aat.dxfy.base.service.OuthCompanyService;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function Oauth2的企业业务实现
 * @info java spring 
 * @update 无
 */
@Service
public class OuthCompanyServiceImpl implements OuthCompanyService {
	@Autowired
	private OuthCompanyDao outhCompanyDao;
	
	
	public int addBean(OuthCompany company) {
		return outhCompanyDao.addBean(company);
	}

	
	public int updateBean(OuthCompany company) {
		return outhCompanyDao.updateBean(company);
	}

	
	public int deleteBean(String id) {
		return outhCompanyDao.deleteBean(id);
	}

	
	public OuthCompany queryBean(String id) {
		return outhCompanyDao.queryBean(id);
	}

	
	public List<OuthCompany> queryAll(Map<String, Object> map) {
		return outhCompanyDao.queryAll(map);
	}


	
	public Long queryAllCount(Map<String, Object> map) {
		return outhCompanyDao.queryAllCount(map);
	}

	public int addOuthCompany(String resposeUrl,
			String eename, String eelogo) {
		OuthCompany company=new OuthCompany();
		company.setId(Atools.getOneKeyS());
		company.setSecret(Atools.getOneKeyS());
		company.setResposeUrl(resposeUrl);
		company.setEename(eename);
		company.setEelogo(eelogo);
		company.setFlag("0");
		
		return outhCompanyDao.addBean(company);
	}


}
