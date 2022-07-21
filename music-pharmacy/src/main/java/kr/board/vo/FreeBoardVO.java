package kr.board.vo;

import java.sql.Date;
  
public class FreeBoardVO {
	private int free_num;
	private String free_title;
	private String free_writer;
	private String free_content;
	private Date free_date;
	private Date free_modify_date;
	private String free_img;
	private int free_hits;
	private int free_code;
	
	public int getFree_num() {
		return free_num;
	}
	public void setFree_num(int free_num) {
		this.free_num = free_num;
	}
	public String getFree_title() {
		return free_title;
	}
	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}
	public String getFree_writer() {
		return free_writer;
	}
	public void setFree_writer(String free_writer) {
		this.free_writer = free_writer;
	}
	public String getFree_content() {
		return free_content;
	}
	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}
	public Date getFree_date() {
		return free_date;
	}
	public void setFree_date(Date free_date) {
		this.free_date = free_date;
	}
	public Date getFree_modify_date() {
		return free_modify_date;
	}
	public void setFree_modify_date(Date free_modify_date) {
		this.free_modify_date = free_modify_date;
	}
	public String getFree_img() {
		return free_img;
	}
	public void setFree_img(String free_img) {
		this.free_img = free_img;
	}
	public int getFree_hits() {
		return free_hits;
	}
	public void setFree_hits(int free_hits) {
		this.free_hits = free_hits;
	}
	public int getFree_code() {
		return free_code;
	}
	public void setFree_code(int free_code) {
		this.free_code = free_code;
	}

	
	
	
}
