package com.aat.dxfy.base.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.Ress;
import com.aat.dxfy.base.bean.Role;
import com.aat.dxfy.base.dao.RessDao;
import com.aat.dxfy.base.dao.RoleDao;
import com.aat.dxfy.base.dao.RoleResDao;
import com.aat.dxfy.base.service.RoleService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 角色业务
 * @info java spring 
 * @update 无
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleResDao roleResDao;

	@Autowired
	private RessDao ressDao;

	
	public int addRole(String role, String desc, String ressId) {
		// 1,
		Role r1 = new Role();
		String roleid = Atools.getOneKeyS();
		r1.setId(roleid);
		r1.setRole(role);
		r1.setState(desc);
		r1.setLocked("0");
		int sum = roleDao.addBean(r1);
		// 2,
		List<String> resIds = Atools.str2List(ressId, ",");
		for (String resId : resIds) {
			Map<String, Object> roleRes = new HashMap<String, Object>();
			roleRes.put("id", Atools.getOneKeyS());
			roleRes.put("roleId", roleid);
			roleRes.put("ressId", resId);
			sum += roleResDao.addBean(roleRes);
		}
		// 3,
		return sum;
	}

	public int updateRole(String id, String role, String desc, String ressId) {
		// 1,
		Role r1 = new Role();
		r1.setId(id);
		r1.setRole(role);
		r1.setState(desc);
		int sum = roleDao.updateBean(r1);
		// 2,
		sum += roleResDao.deleteBean(id);
		// 3,
		List<String> resIds = Atools.str2List(ressId, ",");
		for (String resId : resIds) {
			Map<String, Object> roleRes = new HashMap<String, Object>();
			roleRes.put("id", Atools.getOneKeyS());
			roleRes.put("roleId", id);
			roleRes.put("ressId", resId);
			sum += roleResDao.addBean(roleRes);
		}
		return sum;
	}

	public int lockRole(String id, String locked) {
		// 1,
		Role r1 = new Role();
		r1.setId(id);
		r1.setLocked(locked);
		int sum = roleDao.updateBean(r1);
		return sum;
	}

	public int deleteRole(String id) {
		int sum = roleDao.deleteBean(id);
		sum += roleResDao.deleteBean(id);
		return sum;
	}

	public Role queryRole(String id) {
		return roleDao.queryBean(id);
	}

	public List<String> queryResIds(String id) {
		List<String> lists = roleResDao.queryResIds(id);
		return lists;
	}

	public List<Ress> queryRess(String id) {
		// 1,
		List<String> lists = roleResDao.queryResIds(id);
		List<Ress> resss = new LinkedList<Ress>();
		// 2,
		if (lists.size() == 0) {
			return null;
		}
		// 3,
		for (String rid : lists) {
			resss.add(ressDao.queryBean(rid));
		}
		return resss;
	}

	public List<Role> queryAll(Map<String, Object> map) {
		return roleDao.queryAll(map);
	}

	public long queryAllCount(Map<String, Object> map) {
		return roleDao.queryAllCount(map);
	}

	// 是否锁定的验证，只用于角色资源分配
	public List<Ress> queryRoleRess(String role) {
		List<Ress> resss = new LinkedList<Ress>();
		Role r = roleDao.queryRole(role);
		if (null != r && "0".equals(r.getLocked())) {
			List<String> lists = roleResDao.queryResIds(r.getId());
			for (String rid : lists) {
				if (StringUtils.isNotEmpty(rid)) {
					Ress res = ressDao.queryBean(rid);
					if (null != res && "0".equals(res.getLocked())) {
						resss.add(res);
					}
				}

			}

		}
		return resss;
	}

}
