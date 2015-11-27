package com.aat.dxfy.base.bean;

import java.io.Serializable;
import java.util.*;

public class User1info  implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String uid;
    private String xinzuo;
    private String birthday;
    private String shengxiao;
    private String lovetype;
    private String lovebook;
    private String lovemusic;
    private String lovevideo;
    private String loveweb;
    private String loveother;
    private String ismarry;
    private String ischildren;
    private String cartype;
    private String house;
    private Date systime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getXinzuo() {
        return xinzuo;
    }

    public void setXinzuo(String xinzuo) {
        this.xinzuo = xinzuo;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getShengxiao() {
        return shengxiao;
    }

    public void setShengxiao(String shengxiao) {
        this.shengxiao = shengxiao;
    }

    public String getLovetype() {
        return lovetype;
    }

    public void setLovetype(String lovetype) {
        this.lovetype = lovetype;
    }

    public String getLovebook() {
        return lovebook;
    }

    public void setLovebook(String lovebook) {
        this.lovebook = lovebook;
    }

    public String getLovemusic() {
        return lovemusic;
    }

    public void setLovemusic(String lovemusic) {
        this.lovemusic = lovemusic;
    }

    public String getLovevideo() {
        return lovevideo;
    }

    public void setLovevideo(String lovevideo) {
        this.lovevideo = lovevideo;
    }

    public String getLoveweb() {
        return loveweb;
    }

    public void setLoveweb(String loveweb) {
        this.loveweb = loveweb;
    }

    public String getLoveother() {
        return loveother;
    }

    public void setLoveother(String loveother) {
        this.loveother = loveother;
    }

    public String getIsmarry() {
        return ismarry;
    }

    public void setIsmarry(String ismarry) {
        this.ismarry = ismarry;
    }

    public String getIschildren() {
        return ischildren;
    }

    public void setIschildren(String ischildren) {
        this.ischildren = ischildren;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Date getSystime() {
        return systime;
    }

    public void setSystime(Date systime) {
        this.systime = systime;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User1info other = (User1info) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String toString() {
       return "User1info [id="+ id +"]";
    }

}
