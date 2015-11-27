package com.aat.dxfy.base.service;

import java.util.List;
import java.util.Map;

import com.aat.dxfy.base.bean.OuthToken;

public interface OuthTokenService {
	int addBean(OuthToken token);

	int updateBean(OuthToken token);

	int deleteBean(String id);

	OuthToken queryBean(String id);

	OuthToken queryBeanCUID(String cid, String uid);

	List<OuthToken> queryAll(Map<String, Object> map);

	Long queryAllCount(Map<String, Object> map);

	int addToken(String id, String companyId, String userId, String eename);
}
