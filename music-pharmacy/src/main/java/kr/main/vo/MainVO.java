package kr.main.vo;

import kr.board.vo.FreeBoardVO;
import kr.board.vo.InquiryBoardVO;
import kr.board.vo.NoticeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.music.vo.MusicVO;

public class MainVO {
	private FreeBoardVO free;
	private ThemeBoardVO the;
	private MusicVO mus;
	private InquiryBoardVO inq;
	private NoticeBoardVO notice;
	
	public FreeBoardVO getFree() {
		return free;
	}
	public void setFree(FreeBoardVO free) {
		this.free = free;
	}
	public ThemeBoardVO getThe() {
		return the;
	}
	public void setThe(ThemeBoardVO the) {
		this.the = the;
	}
	public MusicVO getMus() {
		return mus;
	}
	public void setMus(MusicVO mus) {
		this.mus = mus;
	}
	public InquiryBoardVO getInq() {
		return inq;
	}
	public void setInq(InquiryBoardVO inq) {
		this.inq = inq;
	}
	public NoticeBoardVO getNotice() {
		return notice;
	}
	public void setNotice(NoticeBoardVO notice) {
		this.notice = notice;
	}
	
	
}
