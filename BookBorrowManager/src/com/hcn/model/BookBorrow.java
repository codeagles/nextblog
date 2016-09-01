package com.hcn.model;
import java.sql.Date;

public class BookBorrow {
	private String readerid;
	private String ISBN;
	private Date borroedate;
	private Date returndate;
	private Double fune=0.1;
	private String bookname;
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getReaderid() {
		return readerid;
	}
	public void setReaderid(String readerid) {
		this.readerid = readerid;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public Date getBorroedate() {
		return borroedate;
	}
	public void setBorroedate(Date borroedate) {
		this.borroedate = borroedate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public Double getFune() {
		return fune;
	}
	public void setFune(Double fune) {
		this.fune = fune;
	}
}


