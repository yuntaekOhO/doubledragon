package kr.member.vo;

import java.sql.Date;

public class MemberPointVO {
	private Date board_date;
	private int mem_num;
	public Date getBoard_date() {
		return board_date;
	}
	
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
}
