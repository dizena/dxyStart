package com.aat.dxfy.base.bean;

import java.io.Serializable;
import java.util.*;

public class User3job  implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String uid;
    private String eename;
    private String jobName;
    private Integer salary;
    private String startTime;
    private String endTime;
    private String industry;
    private String jobcontext;
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

    public String getEename() {
        return eename;
    }

    public void setEename(String eename) {
        this.eename = eename;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getJobcontext() {
        return jobcontext;
    }

    public void setJobcontext(String jobcontext) {
        this.jobcontext = jobcontext;
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
        User3job other = (User3job) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String toString() {
       return "User3job [id="+ id +"]";
    }

}
