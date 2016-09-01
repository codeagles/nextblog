package com.hcn.model;
import java.sql.Date;

public class Reader {
	private int readerid;
	private int type;
	private String name;
	private int age;
	private String sex;
	private String phone;
	private String dept;
	private Date regdate;
	private String typename;
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getMaxborrownum() {
		return maxborrownum;
	}
	public void setMaxborrownum(int maxborrownum) {
		this.maxborrownum = maxborrownum;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	private int maxborrownum;
	private int limit;
	public int getReaderid() {
		return readerid;
	}
	public void setReaderid(int readerid) {
		this.readerid = readerid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

}
