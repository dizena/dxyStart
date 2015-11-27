package com.aat.dxfy.base.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String passwd;
	private String salt;
	private String roles;

	private String auths;
	private String locked;
	private Date regTime;
	private String nickname;
	private String sex;

	private String icon;
	private String location;
	private String flag;
	private String token;
	private String email;

	private String phone;
	private String truename;
	private Integer age;
	private String minzu;
	private String cardid;

	private String addressNow;
	private String addressHome;
	private String addressJob;
	private String salaryRange;
	private Date systime;

	private String qt1;
	private String answer1;
	private String qt2;
	private String answer2;
	private Integer num01;

	private String str01;
	private String str02;
	private String str03;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAuths() {
		return auths;
	}

	public void setAuths(String auths) {
		this.auths = auths;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMinzu() {
		return minzu;
	}

	public void setMinzu(String minzu) {
		this.minzu = minzu;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getAddressNow() {
		return addressNow;
	}

	public void setAddressNow(String addressNow) {
		this.addressNow = addressNow;
	}

	public String getAddressHome() {
		return addressHome;
	}

	public void setAddressHome(String addressHome) {
		this.addressHome = addressHome;
	}

	public String getAddressJob() {
		return addressJob;
	}

	public void setAddressJob(String addressJob) {
		this.addressJob = addressJob;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public Date getSystime() {
		return systime;
	}

	public void setSystime(Date systime) {
		this.systime = systime;
	}

	public String getQt1() {
		return qt1;
	}

	public void setQt1(String qt1) {
		this.qt1 = qt1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getQt2() {
		return qt2;
	}

	public void setQt2(String qt2) {
		this.qt2 = qt2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public Integer getNum01() {
		return num01;
	}

	public void setNum01(Integer num01) {
		this.num01 = num01;
	}

	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}

	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}

	public String getStr03() {
		return str03;
	}

	public void setStr03(String str03) {
		this.str03 = str03;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
