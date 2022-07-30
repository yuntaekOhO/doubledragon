package kr.board.vo;

import java.sql.Date;

public class InquiryBoardVO {
	private int inq_num;
	private String inq_writer;
	private String inq_question;
	private String inq_answer;
	private Date inq_date;
	private Date inq_modify_date;
	private String inq_img;
	private int mem_num;
	
	private String id;//회원 아이디
	private String nick;//닉네임
	private String photo;//회원 프로필 사진
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getInq_num() {
		return inq_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public void setInq_num(int inq_num) {
		this.inq_num = inq_num;
	}
	public String getInq_writer() {
		return inq_writer;
	}
	public void setInq_writer(String inq_writer) {
		this.inq_writer = inq_writer;
	}
	
	public String getInq_question() {
		return inq_question;
	}
	public void setInq_question(String inq_question) {
		this.inq_question = inq_question;
	}
	public String getInq_answer() {
		return inq_answer;
	}
	public void setInq_answer(String inq_answer) {
		this.inq_answer = inq_answer;
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
	
	
}
