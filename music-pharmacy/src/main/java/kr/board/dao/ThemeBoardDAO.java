package kr.board.dao;

public class ThemeBoardDAO {
	//싱글턴 패턴
	private static ThemeBoardDAO instance = new ThemeBoardDAO();
	
	public static ThemeBoardDAO getInstance() {
		return instance;
	}
	private ThemeBoardDAO() {}
	
}
