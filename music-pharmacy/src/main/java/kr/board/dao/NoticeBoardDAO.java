package kr.board.dao;

public class NoticeBoardDAO {
	//싱글턴 패턴
	private static NoticeBoardDAO instance = new NoticeBoardDAO();
	
	public static NoticeBoardDAO getInstance() {
		return instance;
	}
	private NoticeBoardDAO() {}
}
