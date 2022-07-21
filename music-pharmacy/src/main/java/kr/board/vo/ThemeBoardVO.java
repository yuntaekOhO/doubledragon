package kr.board.vo;

import java.sql.Date;
 
public class ThemeBoardVO {
	private int the_num;
	private String the_title;
	private String the_writer;
	private String the_content;
	private Date the_date;
	private Date the_modify_date;
	private String the_img;
	private int the_code;
	private String the_video;
	private String the_url;
	private String mus_genre; //이거요
	private int the_recommend;
	private int the_hits;
	
	
	public int getThe_num() {
		return the_num;
	}
	public void setThe_num(int the_num) {
		this.the_num = the_num;
	}
	public String getThe_title() {
		return the_title;
	}
	public void setThe_title(String the_title) {
		this.the_title = the_title;
	}
	public String getThe_writer() {
		return the_writer;
	}
	public void setThe_writer(String the_writer) {
		this.the_writer = the_writer;
	}
	public String getThe_content() {
		return the_content;
	}
	public void setThe_content(String the_content) {
		this.the_content = the_content;
	}
	public Date getThe_date() {
		return the_date;
	}
	public void setThe_date(Date the_date) {
		this.the_date = the_date;
	}
	public Date getThe_modify_date() {
		return the_modify_date;
	}
	public void setThe_modify_date(Date the_modify_date) {
		this.the_modify_date = the_modify_date;
	}
	public String getThe_img() {
		return the_img;
	}
	public void setThe_img(String the_img) {
		this.the_img = the_img;
	}
	public int getThe_code() {
		return the_code;
	}
	public void setThe_code(int the_code) {
		this.the_code = the_code;
	}
	public String getThe_video() {
		return the_video;
	}
	public void setThe_video(String the_video) {
		this.the_video = the_video;
	}
	public String getThe_url() {
		return the_url;
	}
	public void setThe_url(String the_url) {
		this.the_url = the_url;
	}
	public String getMus_genre() {
		return mus_genre;
	}
	public void setMus_genre(String mus_genre) {
		this.mus_genre = mus_genre;
	}
	public int getThe_recommend() {
		return the_recommend;
	}
	public void setThe_recommend(int the_recommend) {
		this.the_recommend = the_recommend;
	}
	public int getThe_hits() {
		return the_hits;
	}
	public void setThe_hits(int the_hits) {
		this.the_hits = the_hits;
	}

	
	
}
