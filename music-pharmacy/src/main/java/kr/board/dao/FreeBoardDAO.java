package kr.board.dao;

public class FreeBoardDAO {
	//싱글턴 패턴
	private static FreeBoardDAO instance = new FreeBoardDAO();
	
	public static FreeBoardDAO getInstance() {
		return instance;
	}
	private FreeBoardDAO() {}
	
}
