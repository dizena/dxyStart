package com.aat.dxfy.base.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.OuthToken;
import com.aat.dxfy.base.dao.OuthTokenDao;
import com.aat.dxfy.base.service.OuthTokenService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function Oauth2的Token 业务实现
 * @info java spring 
 * @update 无
 */
@Service
public class OuthTokenServiceImpl implements OuthTokenService {

	@Autowired
	private OuthTokenDao outhTokenDao;

	
	public int addBean(OuthToken token) {
		return outhTokenDao.addBean(token);
	}

	
	public int updateBean(OuthToken token) {
		return outhTokenDao.updateBean(token);
	}

	
	public int deleteBean(String id) {
		return outhTokenDao.deleteBean(id);
	}

	
	public OuthToken queryBean(String id) {
		return outhTokenDao.queryBean(id);
	}

	
	public List<OuthToken> queryAll(Map<String, Object> map) {
		return outhTokenDao.queryAll(map);
	}

	
	public Long queryAllCount(Map<String, Object> map) {
		return outhTokenDao.queryAllCount(map);
	}

	public int addToken(String id, String companyId, String userId,
			String eename) {
		OuthToken tmp = new OuthToken();
		tmp.setId(id);
		tmp.setCompanyId(companyId);
		tmp.setUserId(userId);

		tmp.setStartTime(new Date());
		tmp.setEename(eename);
		tmp.setFlag("0");
		return outhTokenDao.addBean(tmp);
	}

	public OuthToken queryBeanCUID(String cid, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", cid);
		map.put("userId", uid);
		return outhTokenDao.queryBeanCUID(map);
	}

}
