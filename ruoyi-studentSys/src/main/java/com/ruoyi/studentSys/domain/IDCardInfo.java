package com.ruoyi.studentSys.domain;

public class IDCardInfo implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String nation;
    private String address;
    private String idNumber;
    private String birth;
    private String sex;

    private String IssueDate;

    private String IssueOrg;
    private String expDate;

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public String getIssueOrg() {
        return IssueOrg;
    }

    public void setIssueOrg(String issueOrg) {
        IssueOrg = issueOrg;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
