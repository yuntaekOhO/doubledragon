package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.ThemeBoardReVO;
import kr.board.vo.ThemeBoardVO;
import kr.util.DBUtil;

public class MyWriteDAO {
	private static MyWriteDAO instance = new MyWriteDAO();
	public static MyWriteDAO getInstance() {
		return instance;
	}
	private MyWriteDAO() {}
	
	//회원번호로 글 조회하기
	public ThemeBoardVO getBoardByMemNum(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ThemeBoardVO board = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM theme_board b JOIN member m "
					+ "USING(mem_num) JOIN member_detail d "
					+ "USING(mem_num) WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new ThemeBoardVO();
				board.setThe_num(rs.getInt("the_num"));
				board.setThe_title(rs.getString("the_title"));
				board.setNick(rs.getString("nick"));
				board.setThe_content(rs.getString("the_content"));
				board.setThe_date(rs.getDate("the_date"));
				board.setThe_modify_date(rs.getDate("the_modify_date"));
				board.setThe_code(rs.getInt("the_code"));
				board.setThe_video(rs.getString("the_video"));
				board.setThe_url(rs.getString("the_url"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				board.setPhoto(rs.getString("photo"));
				board.setThe_img(rs.getString("the_img"));
				board.setThe_hits(rs.getInt("the_hits"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return board;
	}
	
	//회원번호를 이용해 내가 쓴 댓글 조회 후 댓글번호로 댓글목록 반환
	public List<ThemeBoardReVO> getReplyListByMemNum(int start, int end, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ThemeBoardReVO> list = null;
		ThemeBoardReVO reply = null;
		int treply_num = 0;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			//로그인한 회원번호로 댓글이 작성된 게시글 번호를 구함
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT treply_num FROM theme_comment WHERE mem_num=? ORDER BY treply_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ThemeBoardReVO>();
			ThemeBoardDAO tDao = ThemeBoardDAO.getInstance();
			while(rs.next()) {
				reply = tDao.getReplyBoard(rs.getInt("treply_num"));
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//내가 쓴 댓글 갯수
	public int getMyReplyBoardCount(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM theme_comment b "
					+ "JOIN member m ON b.mem_num=m.mem_num "
					+ "WHERE b.mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
}
