package kr.board.vo;

import java.sql.Date;

public class NoticeBoardVO {
	private int not_num;
	private String not_title;
	private String not_writer;
	private String not_content;
	private Date not_date;
	private Date not_modify_date;
	private String not_img;
	private int not_hits;
	private String not_photo; //관리자 프로필 사진
	private int mem_num;
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getNot_photo() {
		return not_photo;
	}
	public void setNot_photo(String not_photo) {
		this.not_photo = not_photo;
	}
	public int getNot_num() {
		return not_num;
	}
	public void setNot_num(int not_num) {
		this.not_num = not_num;
	}
	public String getNot_title() {
		return not_title;
	}
	public void setNot_title(String not_title) {
		this.not_title = not_title;
	}
	public String getNot_writer() {
		return not_writer;
	}
	public void setNot_writer(String not_writer) {
		this.not_writer = not_writer;
	}
	public String getNot_content() {
		return not_content;
	}
	public void setNot_content(String not_content) {
		this.not_content = not_content;
	}
	public Date getNot_date() {
		return not_date;
	}
	public void setNot_date(Date not_date) {
		this.not_date = not_date;
	}
	public Date getNot_modify_date() {
		return not_modify_date;
	}
	public void setNot_modify_date(Date not_modify_date) {
		this.not_modify_date = not_modify_date;
	}
	public String getNot_img() {
		return not_img;
	}
	public void setNot_img(String not_img) {
		this.not_img = not_img;
	}
	public int getNot_hits() {
		return not_hits;
	}
	public void setNot_hits(int not_hits) {
		this.not_hits = not_hits;
	}
	
	
}
