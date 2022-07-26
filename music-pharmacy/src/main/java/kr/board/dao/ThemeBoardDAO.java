package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.ThemeBoardVO;
import kr.music.vo.MusicVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

import kr.util.StringUtil;

public class ThemeBoardDAO {
	//싱글턴 패턴
	private static ThemeBoardDAO instance = new ThemeBoardDAO();
	
	public static ThemeBoardDAO getInstance() {
		return instance;
	}
	private ThemeBoardDAO() {}
	
	// 글등록
	public void insertBoard(ThemeBoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			// JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			// SQL 문 작성
			sql = "INSERT INTO theme_board(the_num,the_title,the_writer,the_content,the_date,the_img,"
					+ "the_code,the_video,the_url, mem_num) VALUES "
					+ "(board_seq.nextval,?,?,?,SYSDATE,?,?,?,?,?)";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, board.getThe_title());
			pstmt.setString(2, board.getThe_writer());
			pstmt.setString(3, board.getThe_content());
			pstmt.setString(4, board.getThe_img());
			pstmt.setInt(5, board.getThe_code());
			pstmt.setString(6, board.getThe_video());
			pstmt.setString(7, board.getThe_url());
			pstmt.setInt(8, board.getMem_num());
			//pstmt.setString(8, board.getMus_genre());
			
			// JDBC 수행 4단계
			pstmt.executeUpdate();	
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
		// 자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	
	}
	
	//총 레코드 수(검색 레코드 수)
	public int getBoardCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE b.the_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE b.the_content LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM theme_board b JOIN member m USING(mem_num) " + sub_sql;
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
			}
			
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//글목록(검색글 목록)
	public List<ThemeBoardVO> getListBoard(int start, int end, String keyfield,String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ThemeBoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE b.the_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE b.the_content LIKE ?";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM theme_board b JOIN member m "
					+ "USING (mem_num) JOIN member_detail d "
					+ "USING (mem_num) "+ sub_sql
					+ " ORDER BY b.the_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<ThemeBoardVO>();
			while(rs.next()) {
				ThemeBoardVO board = new ThemeBoardVO();
				board.setThe_num(rs.getInt("the_num"));
				board.setThe_title(StringUtil.useNoHtml(rs.getString("the_title")));
				board.setThe_hits(rs.getInt("the_hits"));
				board.setThe_date(rs.getDate("the_date"));
				board.setThe_modify_date(rs.getDate("the_modify_date"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				board.setPhoto(rs.getString("photo"));
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
// 글 상세
	public ThemeBoardVO getBoard(int the_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ThemeBoardVO board = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM theme_board b JOIN member m "
				+ "USING(mem_num) JOIN member_detail d "
				+ "USING(mem_num) WHERE b.the_num=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, the_num);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new ThemeBoardVO();
				board.setThe_num(rs.getInt("the_num"));
				board.setThe_title(rs.getString("the_title"));
				board.setThe_content(rs.getString("the_content"));
				board.setThe_img(rs.getString("the_img"));
				board.setThe_hits(rs.getInt("the_hits"));
				board.setThe_date(rs.getDate("the_date"));
				board.setThe_modify_date(rs.getDate("the_modify_date"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				board.setPhoto(rs.getString("photo"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return board;
	}
	
	//조회수 증가
	public void updateReadcount(int the_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성 
			sql = "UPDATE theme_board SET the_hits=the_hits+1 WHERE the_num=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, the_num);
			//JDBC 수행 4단계
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글수정
		public void updateBoard(ThemeBoardVO board)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(board.getThe_img()!=null) {
					//업로드한 파일이 있는 경우
					sub_sql = ",the_img=?";
				}
				
				sql = "UPDATE theme_board SET the_title=?,the_content=?,"
					+ "the_modify_date=SYSDATE" + sub_sql 
					+ ",the_code=?,the_url=? WHERE the_num=?";
				
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(++cnt, board.getThe_title());
				pstmt.setString(++cnt, board.getThe_content());
				if(board.getThe_img()!=null) {
					pstmt.setString(++cnt, board.getThe_img());
				}
				pstmt.setInt(++cnt, board.getThe_code());
				pstmt.setString(++cnt, board.getThe_url());
				pstmt.setInt(++cnt, board.getThe_num());
				
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
			
		}
	
	//글삭제
		public void deleteBoard(int the_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//오토커밋 해제
				conn.setAutoCommit(false);
				
				//좋아요 삭제
				sql = "DELETE FROM board_fav WHERE the_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, the_num);
				pstmt.executeUpdate();
				//댓글 삭제
				
				//부모글 삭제
				sql = "DELETE FROM theme_board WHERE the_num=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, the_num);
				pstmt3.executeUpdate();
				
				//예외 발생이 없이 정상적으로 SQL문 실행
				conn.commit();
			}catch(Exception e) {
				//예외 발생
				conn.rollback();
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
}
