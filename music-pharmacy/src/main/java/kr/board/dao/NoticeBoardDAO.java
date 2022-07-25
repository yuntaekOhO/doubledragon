package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.NoticeBoardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class NoticeBoardDAO {
	//싱글턴 패턴
	private static NoticeBoardDAO instance = new NoticeBoardDAO();
	
	public static NoticeBoardDAO getInstance() {
		return instance;
	}
	private NoticeBoardDAO() {}

	//공지사항 글 등록
	public void insertBoard(NoticeBoardVO notice)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성 , mem_num?????
			sql = "INSERT INTO notice_board (not_num, not_title, not_content, not_img, mem_num) VALUES (notice_seq.nextval,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, notice.getNot_title());
			pstmt.setString(2, notice.getNot_content());
			pstmt.setString(3, notice.getNot_img());
			pstmt.setInt(4, notice.getMem_num());
			
			//SQL문 실행, 전송된 내용이 insert
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//공지사항 총 레코드 수(검색 레코드 수) ----------------------------------------------------------여기보세여~~
	public int getNoticeCount(String keyfield,String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) { //키워드가 있으면
				if(keyfield.equals("1")) sub_sql = "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE b.content LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM notice_board " + sub_sql;
			// b JOIN member m USING(mem_num)
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) { //키워드가있고, 비어있지도않으면
				pstmt.setString(1, "%"+keyword+"%");
			}
			
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
	
	//공지사항 글 목록
	public List<NoticeBoardVO> getListNoticeBoard(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeBoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE b.content LIKE ?";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM notice_board b JOIN member m "
					+ "USING (mem_num) JOIN member_detail d "
					+ "USING (mem_num) " + sub_sql
					+ " ORDER BY b.not_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeBoardVO>();
			while(rs.next()) {
				NoticeBoardVO board = new NoticeBoardVO();
				board.setNot_num(rs.getInt("not_num"));
				board.setNot_title(StringUtil.useNoHtml(rs.getString("not_title")));
				board.setNot_writer(rs.getString("not_writer"));
				board.setNot_content(rs.getString("not_content"));
				board.setNot_date(rs.getDate("not_date"));
				board.setNot_modify_date(rs.getDate("not_modify_date"));
				board.setNot_img(rs.getString("not_img"));
				board.setNot_hits(rs.getInt("not_hits"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setNot_photo(rs.getString("photo"));
			
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
	
	//공지사항 글 상세 (클릭했을때 보이게할)
	public NoticeBoardVO getNoticeBoard(int noticeBoard_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeBoardVO board = null;
		String sql = null;
	
		try {
			//JDBC 수행 1,2단계 :커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM notice_board b JOIN member m "
					+ "USING(mem_num) JOIN member_detail d "
					+ "USING(mem_num) WHERE b.noticeBoard_num=?";
		
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, noticeBoard_num);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new NoticeBoardVO();
				board.setNot_num(rs.getInt("not_num"));
				board.setNot_title(rs.getString("not_title"));
				board.setNot_content(rs.getString("not_content"));
				board.setNot_hits(rs.getInt("not_hits"));
				board.setNot_date(rs.getDate("not_date"));
				board.setNot_modify_date(rs.getDate("not_modify_date"));
				board.setNot_img(rs.getString("not_img"));
//				board.setnotMem_num(rs.getInt("mem_num"));
				board.setNot_writer("not_writer");
				board.setNot_photo(rs.getString("not_photo"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return board;
	}
	
	// 조회수 증가
		public void updateReadcount(int not_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "UPDATE notice_board SET hit=hit+1 WHERE not_num=?";
				// JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, not_num);
				// JDBC 수행 4단계
				pstmt.executeUpdate();

			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				// 자원정리
				DBUtil.executeClose(null, pstmt, conn);

			}
		}
}










