package kr.board.vo;

import java.sql.Date;

public class ThemeBoardReVO {
	 
	private int treply_num;
	private int the_num;
//	private String treply_writer;
	private String treply_content;
	private String treply_date;
	private String treply_modify_date;
	private int mem_num;
	
	private String id;//회원 아이디
	
	public int getTreply_num() {
		return treply_num;
	}
	public void setTreply_num(int treply_num) {
		this.treply_num = treply_num;
	}
	public int getThe_num() {
		return the_num;
	}
	public void setThe_num(int the_num) {
		this.the_num = the_num;
	}
//	public String getTreply_writer() {
//		return treply_writer;
//	}
//	public void setTreply_writer(String treply_writer) {
//		this.treply_writer = treply_writer;
//	}
	public String getTreply_content() {
		return treply_content;
	}
	public void setTreply_content(String treply_content) {
		this.treply_content = treply_content;
	}
	public String getTreply_date() {
		return treply_date;
	}
	public void setTreply_date(String treply_date) {
		this.treply_date = treply_date;
	}
	public String getTreply_modify_date() {
		return treply_modify_date;
	}
	public void setTreply_modify_date(String treply_modify_date) {
		this.treply_modify_date = treply_modify_date;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
