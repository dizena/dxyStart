package com.aat.dxfy.base.bean;

import java.io.Serializable;
import java.util.Date;

public class OuthCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String secret;
	private String resposeUrl;
	private String eename;
	private String eelogo;
	private String eeurl;
	private String bindUserid;
	private Date systime;
	private String flag;// 0禁用 1可用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getResposeUrl() {
		return resposeUrl;
	}

	public void setResposeUrl(String resposeUrl) {
		this.resposeUrl = resposeUrl;
	}

	public String getEename() {
		return eename;
	}

	public void setEename(String eename) {
		this.eename = eename;
	}

	public String getEelogo() {
		return eelogo;
	}

	public void setEelogo(String eelogo) {
		this.eelogo = eelogo;
	}

	public String getEeurl() {
		return eeurl;
	}

	public void setEeurl(String eeurl) {
		this.eeurl = eeurl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBindUserid() {
		return bindUserid;
	}

	public void setBindUserid(String bindUserid) {
		this.bindUserid = bindUserid;
	}

	public Date getSystime() {
		return systime;
	}

	public void setSystime(Date systime) {
		this.systime = systime;
	}

}
