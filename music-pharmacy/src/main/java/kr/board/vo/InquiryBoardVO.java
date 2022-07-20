package kr.board.vo;

import java.sql.Date;

public class InquiryBoardVO {
	private int inq_num;
	private String inq_title;
	private String inq_writer;
	private String inq_content;
	private Date inq_date;
	private Date inq_modify_date;
	private String inq_img;
	private int ireply_num;
	private String ireply_writer;
	private String ireply_content;
	private Date ireply_date;
	private Date ireply_modify_date;
	
	public int getInq_num() {
		return inq_num;
	}
	public void setInq_num(int inq_num) {
		this.inq_num = inq_num;
	}
	public String getInq_title() {
		return inq_title;
	}
	public void setInq_title(String inq_title) {
		this.inq_title = inq_title;
	}
	public String getInq_writer() {
		return inq_writer;
	}
	public void setInq_writer(String inq_writer) {
		this.inq_writer = inq_writer;
	}
	public String getInq_content() {
		return inq_content;
	}
	public void setInq_content(String inq_content) {
		this.inq_content = inq_content;
	}
	public Date getInq_date() {
		return inq_date;
	}
	public void setInq_date(Date inq_date) {
		this.inq_date = inq_date;
	}
	public Date getInq_modify_date() {
		return inq_modify_date;
	}
	public void setInq_modify_date(Date inq_modify_date) {
		this.inq_modify_date = inq_modify_date;
	}
	public String getInq_img() {
		return inq_img;
	}
	public void setInq_img(String inq_img) {
		this.inq_img = inq_img;
	}
	public int getIreply_num() {
		return ireply_num;
	}
	public void setIreply_num(int ireply_num) {
		this.ireply_num = ireply_num;
	}
	public String getIreply_writer() {
		return ireply_writer;
	}
	public void setIreply_writer(String ireply_writer) {
		this.ireply_writer = ireply_writer;
	}
	public String getIreply_content() {
		return ireply_content;
	}
	public void setIreply_content(String ireply_content) {
		this.ireply_content = ireply_content;
	}
	public Date getIreply_date() {
		return ireply_date;
	}
	public void setIreply_date(Date ireply_date) {
		this.ireply_date = ireply_date;
	}
	public Date getIreply_modify_date() {
		return ireply_modify_date;
	}
	public void setIreply_modify_date(Date ireply_modify_date) {
		this.ireply_modify_date = ireply_modify_date;
	}
	
	
}
