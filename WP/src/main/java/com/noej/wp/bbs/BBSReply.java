package com.noej.wp.bbs;

import java.util.Date;

public class BBSReply {
	private int no;
	private int wbNo;
	private String writer;
	private String txt;
	private Date date;
	public BBSReply() {
		// TODO Auto-generated constructor stub
	}
	public BBSReply(int no, int wbNo, String writer, String txt, Date date) {
		super();
		this.no = no;
		this.wbNo = wbNo;
		this.writer = writer;
		this.txt = txt;
		this.date = date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getWbNo() {
		return wbNo;
	}
	public void setWbNo(int wbNo) {
		this.wbNo = wbNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
