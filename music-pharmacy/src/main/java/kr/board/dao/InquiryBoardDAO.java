package kr.board.dao;

public class InquiryBoardDAO {
	//싱글턴 패턴
	private static InquiryBoardDAO instance = new InquiryBoardDAO();
	
	public static InquiryBoardDAO getInstance() {
		return instance;
	}
	private InquiryBoardDAO() {}
	
}
