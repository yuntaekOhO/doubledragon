package kr.board.vo;

import java.sql.Date;

public class FreeBoardReVO {
	 
	private int freply_num;
	private String freply_writer;
	private String freply_content;
	private Date freply_date;
	private Date freply_modify_date;
	
	public int getFreply_num() {
		return freply_num;
	}
	public void setFreply_num(int freply_num) {
		this.freply_num = freply_num;
	}
	public String getFreply_writer() {
		return freply_writer;
	}
	public void setFreply_writer(String freply_writer) {
		this.freply_writer = freply_writer;
	}
	public String getFreply_content() {
		return freply_content;
	}
	public void setFreply_content(String freply_content) {
		this.freply_content = freply_content;
	}
	public Date getFreply_date() {
		return freply_date;
	}
	public void setFreply_date(Date freply_date) {
		this.freply_date = freply_date;
	}
	public Date getFreply_modify_date() {
		return freply_modify_date;
	}
	public void setFreply_modify_date(Date freply_modify_date) {
		this.freply_modify_date = freply_modify_date;
	}
	
	
}
