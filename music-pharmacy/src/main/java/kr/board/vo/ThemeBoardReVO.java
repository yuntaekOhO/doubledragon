package kr.board.vo;

import java.sql.Date;

public class ThemeBoardReVO {
	 
	private int treply_num;
	private int theme_num;
	private String treply_writer;
	private String treply_content;
	private Date treply_date;
	private Date treply_modify_date;
	 
	public int getTreply_num() {
		return treply_num;
	}
	public void setTreply_num(int treply_num) {
		this.treply_num = treply_num;
	}
	public int getTheme_num() {
		return theme_num;
	}
	public void setTheme_num(int theme_num) {
		this.theme_num = theme_num;
	}
	public String getTreply_writer() {
		return treply_writer;
	}
	public void setTreply_writer(String treply_writer) {
		this.treply_writer = treply_writer;
	}
	public String getTreply_content() {
		return treply_content;
	}
	public void setTreply_content(String treply_content) {
		this.treply_content = treply_content;
	}
	public Date getTreply_date() {
		return treply_date;
	}
	public void setTreply_date(Date treply_date) {
		this.treply_date = treply_date;
	}
	public Date getTreply_modify_date() {
		return treply_modify_date;
	}
	public void setTreply_modify_date(Date treply_modify_date) {
		this.treply_modify_date = treply_modify_date;
	}
	
	
}
