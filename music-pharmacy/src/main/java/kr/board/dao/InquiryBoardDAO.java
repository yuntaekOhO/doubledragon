package kr.board.dao;

import kr.board.vo.InquiryBoardVO;

public class InquiryBoardDAO {
	//싱글턴 패턴
	private static InquiryBoardDAO instance = new InquiryBoardDAO();
	
	public static InquiryBoardDAO getInstance() {
		return instance;
	}
	private InquiryBoardDAO() {}
	
	//게시글 작성
	public void insertInquiryBoard(InquiryBoardVO board)throws Exception{
		
	}
	//게시글 목록
}
