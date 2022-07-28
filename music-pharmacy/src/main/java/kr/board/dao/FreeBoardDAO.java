package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardFavVO;
import kr.board.vo.FreeBoardReVO;
import kr.board.vo.FreeBoardVO;

import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class FreeBoardDAO {
	//싱글턴 패턴
	private static FreeBoardDAO instance = new FreeBoardDAO();

	public static FreeBoardDAO getInstance() {
		return instance;
	}
	private FreeBoardDAO() {}

	//글등록
	public void insertBoard(FreeBoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO free_board (free_num,free_title"
					+ ",free_content,free_date,free_img,"
					+ "free_code,mem_num) VALUES ("
					+ "board_seq.nextval,?,?,SYSDATE,?,?,?)";
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, board.getFree_title());
			pstmt.setString(2, board.getFree_content());
			pstmt.setString(3, board.getFree_img());
			pstmt.setInt(4, board.getFree_code());
			pstmt.setInt(5, board.getMem_num());
			

			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();

		}catch(Exception e){
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 레코드 수(검색 레코드 수)
	public int getBoardCount(String keyfield,String keyword)
			throws Exception{
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
				if(keyfield.equals("1")) sub_sql = "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE b.content LIKE ?";
			}

			sql = "SELECT COUNT(*) FROM free_board " + sub_sql;

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
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//글목록(검색글 목록)
		public List<FreeBoardVO> getListBoard(int start, int end,
			          String keyfield,String keyword)
		                             throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FreeBoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
		conn = DBUtil.getConnection();
		
		if(keyword!=null && !"".equals(keyword)) {
		if(keyfield.equals("1")) sub_sql = "WHERE b.title LIKE ?";
		else if(keyfield.equals("2")) sub_sql = "WHERE m.id LIKE ?";
		else if(keyfield.equals("3")) sub_sql = "WHERE b.content LIKE ?";
		}
		
		
		 sql = "SELECT * FROM (SELECT a.*, rownum rnum " +
		 "FROM (SELECT * FROM free_board b JOIN member m " +
		 "USING (mem_num) JOIN member_detail d " + "USING (mem_num) "+ sub_sql +
		 " ORDER BY b.free_num DESC)a) " + "WHERE rnum >= ? AND rnum <= ?";
		 
		
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		if(keyword!=null && !"".equals(keyword)) {
		pstmt.setString(++cnt, "%"+keyword+"%");
		}
		pstmt.setInt(++cnt, start);
		pstmt.setInt(++cnt, end);
		
		//JDBC 수행 4단계
		rs = pstmt.executeQuery();
		list = new ArrayList<FreeBoardVO>();
		while(rs.next()) {
			FreeBoardVO board = new FreeBoardVO();
			board.setFree_num(rs.getInt("free_num"));
			board.setFree_title(StringUtil.useNoHtml(rs.getString("free_title")));
			board.setFree_content(StringUtil.shortWords(20,rs.getString("free_content")));
			board.setFree_date(rs.getDate("free_date"));
			board.setFree_modify_date(rs.getDate("free_modify_date"));
			board.setFree_img(rs.getString("free_img"));
			board.setFree_hits(rs.getInt("free_hits"));
			board.setFree_code(rs.getInt("free_code"));
			board.setId(rs.getString("id"));
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
	//글상세
		public FreeBoardVO getBoard(int free_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			FreeBoardVO board = null;
			String sql = null;
			
			try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				
				 sql = "SELECT * FROM free_board b JOIN member m " +
				 "USING(mem_num) JOIN member_detail d " +
				 "USING(mem_num) WHERE b.free_num=?";
				 
				//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, free_num);
				//JDBC 수행 4단계
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board = new FreeBoardVO();
					board.setFree_num(rs.getInt("free_num"));
					board.setFree_title(StringUtil.useNoHtml(rs.getString("free_title")));
					board.setFree_content(rs.getString("free_content"));
					board.setFree_date(rs.getDate("free_date"));
					board.setFree_modify_date(rs.getDate("free_modify_date"));
					board.setFree_img(rs.getString("free_img"));
					board.setFree_hits(rs.getInt("free_hits"));
					board.setFree_code(rs.getInt("free_code"));
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
	public void updateReadcount(int free_num)
									throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성 
			sql = "UPDATE free_board SET free_hits=free_hits+1 WHERE free_num=?";

			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, free_num);
			//JDBC 수행 4단계
			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//파일삭제
	public void deleteFile(int free_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE free_board SET free_img='' WHERE free_num=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글수정
	
	  public void updateBoard(FreeBoardVO board)throws Exception{ 
		  Connection conn = null; 
		  PreparedStatement pstmt = null; 
		  String sql = null; 
		  String sub_sql = "";
	  int cnt = 0;

	  try { 
		  //커넥션풀로부터 커넥션 할당 
		  conn = DBUtil.getConnection();
	  

	  if(board.getFree_img()!=null) { //업로드한 파일이 있는 경우 
		  sub_sql = ",free_img=?"; 

	  }

	  sql = "UPDATE free_board SET free_title=?,free_content=?," + "free_modify_date=SYSDATE" +
			  sub_sql + " WHERE free_num=?";

	  //PreparedStatement 객체 생성 
	  pstmt = conn.prepareStatement(sql); 
	  //?에 데이터 바인딩
	  pstmt.setString(++cnt, board.getFree_title()); 
	  pstmt.setString(++cnt,board.getFree_content()); 
	  if(board.getFree_img()!=null) { 
		pstmt.setString(++cnt,board.getFree_img()); } 
		pstmt.setInt(++cnt, board.getFree_num());
      //SQL문 실행 
			 pstmt.executeUpdate(); 
	   
	  }catch(Exception e) { 
		  throw new Exception(e); 
	  }finally { 
		//자원정리
			DBUtil.executeClose(null, pstmt, conn); }
		}
				  
	
	//글삭제
	public void deleteBoard(int free_num)throws Exception{
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
			
			//댓글 삭제
			
			//부모글 삭제
			sql = "DELETE FROM free_board WHERE free_num=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, free_num);
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
	//좋아요 등록
		public void insertFav(int free_num, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO board_fav (fav_num,free_num,mem_num) VALUES (zboardfav_seq.nextval,?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, free_num);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//좋아요 개수
		public int selectFavCount(int free_num)
				                        throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM board_fav WHERE free_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, free_num);
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
		//회원번호와 게시물 번호를 이용한 좋아요 정보
		public BoardFavVO selectFav(int free_num, int mem_num)
		                                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardFavVO fav = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM board_fav WHERE free_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, free_num);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					fav = new BoardFavVO();
					fav.setFav_num(rs.getInt("fav_num"));
					fav.setFree_num(rs.getInt("free_num"));
					fav.setMem_num(rs.getInt("mem_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return fav;
		}
		//좋아요 삭제
		public void deleteFav(int fav_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM board_fav WHERE fav_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, fav_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//내가 선택한 좋아요 목록
		public List<FreeBoardVO> getListBoardFav(int start,int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<FreeBoardVO> list = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "SELECT * FROM (SELECT a.*, rownum rum FROM "
						+ "(SELECT * FROM free_board b JOIN member m USING(mem_num) "
						+ "JOIN board_fav f USING(the_num) WHERE f.mem_num=? "
						+ "ORDER BY free_num DESC)a) WHERE rnum >= ? AND rnum<=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, mem_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<FreeBoardVO>();
				while(rs.next()) {
					FreeBoardVO board = new FreeBoardVO();
					board.setFree_num(rs.getInt("free_num"));
					board.setFree_title(StringUtil.useNoHtml(rs.getString("free_title")));
					board.setFree_date(rs.getDate("free_date"));
					board.setId(rs.getString("id"));
					
					list.add(board);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//댓글 등록
		public void insertReplyBoard(FreeBoardReVO boardReply)
		                                 throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO free_comment (frely_num,"
					+ "frely_content,mem_num,free_num) "
					+ "VALUES (freecomment_seq.nextval,?,?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(1, boardReply.getFreply_content());
				pstmt.setInt(2, boardReply.getMem_num());
				pstmt.setInt(3, boardReply.getFree_num());
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//댓글 개수
		public int getReplyBoardCount(int free_num)
				                            throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM free_comment b "
					+ "JOIN member m ON b.mem_num=m.mem_num "
					+ "WHERE b.free_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, free_num);
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
		//댓글 목록
		public List<FreeBoardReVO> getListReplyBoard(int start,
				                int end, int free_num)
		                                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<FreeBoardReVO> list = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM free_comment b "
					+ "JOIN member m USING (mem_num) "
					+ "WHERE b.free_num=? ORDER BY b.frely_num "
					+ "DESC)a) WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, free_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<FreeBoardReVO>();
				while(rs.next()) {
					FreeBoardReVO reply = new FreeBoardReVO();
					reply.setFreply_num(rs.getInt("frely_num"));
					//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
					reply.setFreply_date(
						DurationFromNow.getTimeDiffLabel(
									rs.getString("frely_date")));
					if(rs.getString("frely_modify_date")!=null) {
						reply.setFreply_modify_date(
						 DurationFromNow.getTimeDiffLabel(
								  rs.getString("frely_modify_date")));
					}
					reply.setFreply_content(
							StringUtil.useBrNoHtml(
								    rs.getString("frely_content")));
					reply.setFree_num(rs.getInt("free_num"));
					reply.setMem_num(rs.getInt("mem_num"));
					reply.setId(rs.getString("id"));
					
					
					list.add(reply);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			return list;
		}
		//댓글 상세
		public FreeBoardReVO getReplyBoard(int frely_num)
		                                   throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			FreeBoardReVO reply = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM free_comment WHERE frely_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, frely_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					reply = new FreeBoardReVO();
					reply.setFreply_num(rs.getInt("frely_num"));
					reply.setMem_num(rs.getInt("mem_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			return reply;
		}
		//댓글 수정
		public void updateReplyBoard(FreeBoardReVO reply)
			                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE free_comment SET frely_content=?, frely_modify_date=SYSDATE WHERE frely_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setString(1, reply.getFreply_content());
			pstmt.setInt(2, reply.getFreply_num());
			//SQL문 실행
			pstmt.executeUpdate();
			
			}catch(Exception e) {
			throw new Exception(e);
			}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//댓글 삭제
		public void deleteReplyBoard(int frely_num)
				                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM free_comment WHERE frely_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, frely_num);
				//SQL문 실행
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

}
