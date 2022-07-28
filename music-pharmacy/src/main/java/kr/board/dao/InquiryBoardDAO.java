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
			sql = "INSERT INTO inquiry_board (inq_num,inq_writer,inq_question,inq_answer,inq_date,mem_num,inq_img) "
					+ "VALUES (inq_seq.nextval,?,?,?,SYSDATE,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getInq_writer());
			pstmt.setString(2, board.getInq_question());
			pstmt.setString(3, board.getInq_answer());
			pstmt.setInt(4, board.getMem_num());
			pstmt.setString(5, board.getInq_img());
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
				if(keyfield.equals("1")) sub_sql = "WHERE inq_question LIKE ?";
				if(keyfield.equals("2")) sub_sql = "WHERE inq_answer LIKE ?";
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
				if(keyfield.equals("1")) sub_sql = "WHERE b.inq_question LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE b.inq_answer LIKE ?";
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
				board.setInq_writer(rs.getString("inq_writer"));
				board.setInq_question(rs.getString("inq_question"));
				board.setInq_answer(rs.getString("inq_answer"));
				board.setInq_date(rs.getDate("inq_date"));
				board.setInq_modify_date(rs.getDate("inq_modify_date"));
				board.setInq_img(rs.getString("inq_img"));
				
				list.add(board);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//이미지 삭제
	public void deleteImg(int inq_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE inquiry_board set filename='' WHERE inq_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inq_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//게시글 한 건 조회
	public InquiryBoardVO getBoard(int inq_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InquiryBoardVO board = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM inquiry_board where inq_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inq_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new InquiryBoardVO();
				board.setInq_num(rs.getInt("inq_num"));
				board.setInq_writer(rs.getString("inq_writer"));
				board.setInq_question(rs.getString("inq_question"));
				board.setInq_answer(rs.getString("inq_answer"));
				board.setInq_date(rs.getDate("inq_date"));
				board.setInq_modify_date(rs.getDate("inq_modify_date"));
				board.setInq_img(rs.getString("inq_img"));
				board.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return board;
	}
	//한건의 게시글의 앞 뒤 게시글 알아내기
	public int[] getPreOrNextBoard(int inq_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int[] numArray = new int[2];
		
		try {
			conn = DBUtil.getConnection();
			sql = "select lag, lead from (select inq_num,lag(inq_num,1,0) over (order by inq_num) as lag, lead(inq_num,1,0) over (order by inq_num) as lead from inquiry_board) where inq_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inq_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				numArray[0] = rs.getInt(1);
				numArray[1] = rs.getInt(2);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return numArray;
	}
	//게시글 삭제
	public void deleteInqBoard(int inq_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "DELETE FROM inquiry_board WHERE inq_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inq_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 수정
	public void updateInqBoard(InquiryBoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			if(board.getInq_img()!=null) {
				sub_sql = ",inq_img=?";
			}
			
			sql = "UPDATE inquiry_board SET inq_question=?,inq_answer=?,inq_modify_date=SYSDATE"+sub_sql+" WHERE inq_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, board.getInq_question());
			pstmt.setString(++cnt, board.getInq_answer());
			if(board.getInq_img()!=null) {
				pstmt.setString(++cnt, board.getInq_img());
			}
			pstmt.setInt(++cnt, board.getInq_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
