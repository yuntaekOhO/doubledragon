package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.InquiryBoardVO;
import kr.util.DBUtil;

public class InquiryBoardDAO {
	//싱글턴 패턴
	private static InquiryBoardDAO instance = new InquiryBoardDAO();
	
	public static InquiryBoardDAO getInstance() {
		return instance;
	}
	private InquiryBoardDAO() {}
	
	//게시글 작성
	public void insertInquiryBoard(InquiryBoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO inquiry_board (inq_num,inq_title,inq_writer,inq_question,inq_answer,inq_date) "
					+ "VALUES (inq_seq.nextval,?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getInq_title());
			pstmt.setString(2, board.getInq_writer());
			pstmt.setString(3, board.getInq_question());
			pstmt.setString(4, board.getInq_answer());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 레코드 수
	public int getInqBoardCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE inq_title LIKE ?";
				if(keyfield.equals("2")) sub_sql = "WHERE inq_question LIKE ?";
				if(keyfield.equals("3")) sub_sql = "WHERE inq_answer title LIKE ?";
			}
			sql = "SELECT COUNT(*) FROM inquiry_board " + sub_sql;
			
			pstmt =  conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
			}
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
	//게시글 목록
	public List<InquiryBoardVO> selectList(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<InquiryBoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE inq_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE inq_question LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE inq_answer LIKE ?";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM inquiry_board b JOIN member m "
					+ "USING (mem_num) JOIN member_detail d USING (mem_num) " + sub_sql + " ORDER BY b.inq_num desc)a) "
							+ "WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<InquiryBoardVO>();
			while(rs.next()) {
				InquiryBoardVO board = new InquiryBoardVO();
				board.setInq_num(rs.getInt("inq_num"));
				board.setInq_title(rs.getString("inq_title"));
				board.setInq_writer(rs.getString("inq_writer"));
				board.setInq_question(rs.getString("inq_question"));
				board.setInq_answer(rs.getString("inq_answer"));
				board.setInq_date(rs.getDate("inq_date"));
				board.setInq_modify_date(rs.getDate("inq_modify_date"));
				board.setInq_img(rs.getString("inq_img"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
}
