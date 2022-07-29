package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardFavVO;
import kr.board.vo.ThemeBoardReVO;
import kr.board.vo.ThemeBoardVO;
import kr.music.vo.MusicVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class ThemeBoardDAO {
	//싱글턴 패턴
	private static ThemeBoardDAO instance = new ThemeBoardDAO();
	
	public static ThemeBoardDAO getInstance() {
		return instance;
	}
	private ThemeBoardDAO() {}
	
	// 글등록
	public void insertBoard(ThemeBoardVO board, MusicVO music)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; //시퀀스 번호 저장
		
		try {
			// JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			// 오토 커밋 해제
			conn.setAutoCommit(false);
			
			// the_num 생성
			sql = "SELECT board_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			// SQL 문 작성
			sql = "INSERT INTO theme_board(the_num,the_title,the_writer,the_content,the_date,the_img,"
					+ "the_code,the_video,the_url, mem_num) VALUES "
					+ "(?,?,?,?,SYSDATE,?,?,?,?,?)";
			// JDBC 수행 3단계
			pstmt2 = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt2.setInt(1, num);
			pstmt2.setString(2, board.getThe_title());
			pstmt2.setString(3, board.getThe_writer());
			pstmt2.setString(4, board.getThe_content());
			pstmt2.setString(5, board.getThe_img());
			pstmt2.setInt(6, board.getThe_code());
			pstmt2.setString(7, board.getThe_video());
			pstmt2.setString(8, board.getThe_url());
			pstmt2.setInt(9, board.getMem_num());

			// JDBC 수행 4단계
			pstmt2.executeUpdate();	
			
			sql = "INSERT INTO music(mus_num,the_num,mus_album,mus_singer,mus_title,mus_genre,mus_img,mus_date,"
					+ "mus_composer,mus_songwriter) VALUES "
					+ "(music_seq.nextval,?,?,?,?,?,?,?,?,?)";
			
			pstmt3 = conn.prepareStatement(sql);
			// ?에 데이터 바인딩

			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, music.getMus_album());
			pstmt3.setString(3, music.getMus_singer());
			pstmt3.setString(4, music.getMus_title());
			pstmt3.setString(5, music.getMus_genre());
			pstmt3.setString(6, music.getMus_img());
			pstmt3.setString(7, music.getMus_date());
			pstmt3.setString(8, music.getMus_composer());
			pstmt3.setString(9, music.getMus_songwriter());
			
			pstmt3.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
		// 자원 정리
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
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
				else if(keyfield.equals("2")) sub_sql = "WHERE d.nick LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE b.the_content LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM theme_board b JOIN member m USING(mem_num) JOIN member_detail d USING(mem_num) " + sub_sql;
			
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
	
	//게시판별 총 레코드 수(검색 레코드 수)
		public int getSubBoardCount(String keyfield, String keyword, int code) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			String sub_sql1 = "WHERE b.the_code=? ";
			int count = 0;
			int cnt = 0;
			//code = 0;
			
			try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(keyword!=null && !"".equals(keyword)) {
					if(keyfield.equals("1")) sub_sql = "AND b.the_title LIKE ?";
					else if(keyfield.equals("2")) sub_sql = "AND d.nick LIKE ?";
					else if(keyfield.equals("3")) sub_sql = "AND b.the_content LIKE ?";
				}
				
				sql = "SELECT COUNT(*) FROM theme_board b JOIN member m USING(mem_num) JOIN member_detail d USING(mem_num) " + sub_sql1 + sub_sql;
				
				//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(++cnt, code);
				if(keyword!=null && !"".equals(keyword)) {
					pstmt.setString(++cnt, "%"+keyword+"%");
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
				else if(keyfield.equals("2")) sub_sql = "WHERE d.nick LIKE ?";
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
				board.setNick(rs.getString("nick"));
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
	
	//게시판별 글목록(검색글 목록)
		public List<ThemeBoardVO> getSubListBoard(int start, int end, String keyfield,String keyword, int code) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ThemeBoardVO> list = null;
			String sql = null;
			String sub_sql = "";
			String sub_sql1 = "WHERE b.the_code=? ";
			int cnt = 0;
			//code = 0;
			
			try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(keyword!=null && !"".equals(keyword)) {
					if(keyfield.equals("1")) sub_sql = "AND b.the_title LIKE ?";
					else if(keyfield.equals("2")) sub_sql = "AND d.nick LIKE ?";
					else if(keyfield.equals("3")) sub_sql = "AND b.the_content LIKE ?";
				}
				
				sql = "SELECT * FROM (SELECT a.*, rownum rnum "
						+ "FROM (SELECT * FROM theme_board b JOIN member m "
						+ "USING (mem_num) JOIN member_detail d "
						+ "USING (mem_num) "+ sub_sql1 + sub_sql
						+ " ORDER BY b.the_num DESC)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(++cnt, code);
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
					board.setThe_code(rs.getInt("the_code"));
					board.setNick(rs.getString("nick"));
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
				board.setThe_code(rs.getInt("the_code"));
				board.setThe_video(rs.getString("the_video"));
				board.setThe_url(rs.getString("the_url"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setNick(rs.getString("nick"));
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
	
	// music 상세
	public MusicVO getMusic(int the_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MusicVO music = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM music m JOIN theme_board USING(the_num) WHERE the_num=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, the_num);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				music = new MusicVO();
				music.setThe_num(rs.getInt("the_num"));
				music.setMus_num(rs.getInt("mus_num"));
				music.setMus_title(rs.getString("mus_title"));
				music.setMus_img(rs.getString("mus_img"));
				music.setMus_album(rs.getString("mus_album"));
				music.setMus_title(rs.getString("mus_title"));
				music.setMus_genre(rs.getString("mus_genre"));
				music.setMus_singer(rs.getString("mus_singer"));
				music.setMus_date(rs.getString("mus_date"));
				music.setMus_composer(rs.getString("mus_composer"));
				music.setMus_songwriter(rs.getString("mus_songwriter"));
				music.setMus_recommend(rs.getInt("mus_recommend"));
				music.setMus_hits(rs.getInt("mus_hits"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return music;
	}
	
	// music 상세
		public MusicVO getMusicFav(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MusicVO music = null;
			String sql = null;
			
					
			try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM music JOIN board_fav USING(the_num) JOIN member USING(mem_num) WHERE mem_num=?";
				
				//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				//JDBC 수행 4단계
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					music = new MusicVO();
					music.setThe_num(rs.getInt("the_num"));
					music.setMus_num(rs.getInt("mus_num"));
					music.setMus_title(rs.getString("mus_title"));
					music.setMus_img(rs.getString("mus_img"));
					music.setMus_album(rs.getString("mus_album"));
					music.setMus_title(rs.getString("mus_title"));
					music.setMus_genre(rs.getString("mus_genre"));
					music.setMus_singer(rs.getString("mus_singer"));
					music.setMus_date(rs.getString("mus_date"));
					music.setMus_composer(rs.getString("mus_composer"));
					music.setMus_songwriter(rs.getString("mus_songwriter"));
					music.setMus_recommend(rs.getInt("mus_recommend"));
					music.setMus_hits(rs.getInt("mus_hits"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return music;
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
		public void updateBoard(ThemeBoardVO board, MusicVO music)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			int cnt1 = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				// 오토 커밋 해제
				conn.setAutoCommit(false);
				
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
				
				pstmt.executeUpdate();
				
				if(music.getMus_img()!=null) {
					//업로드한 파일이 있는 경우
					sub_sql = ",mus_img=?";
				}
				
				sql = "UPDATE music SET mus_album=?,mus_singer=?,mus_title=?,mus_genre=?,"
						+ sub_sql + "mus_composer=?,mus_songwriter=? WHERE the_num=?";
						
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setString(++cnt1, music.getMus_album());
				pstmt2.setString(++cnt1, music.getMus_singer());
				pstmt2.setString(++cnt1, music.getMus_title());
				pstmt2.setString(++cnt1, music.getMus_genre());
				if(music.getMus_img()!=null) {
					pstmt2.setString(++cnt1, music.getMus_img());
				}
				pstmt2.setString(++cnt1, music.getMus_composer());
				pstmt2.setString(++cnt1, music.getMus_songwriter());
				pstmt2.setInt(++cnt1, music.getThe_num());
				
				//SQL문 실행
				pstmt2.executeUpdate();
				
				conn.commit();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt2, null);
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

		// 좋아요 등록 
		public void insertFav(int the_num, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO board_fav (fav_num,the_num,mem_num) VALUES (zboardfav_seq.nextval,?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, the_num);
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
		public int selectFavCount(int the_num)
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
				sql = "SELECT COUNT(*) FROM board_fav WHERE the_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, the_num);
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
		public BoardFavVO selectFav(int the_num, int mem_num)
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
				sql = "SELECT * FROM board_fav WHERE the_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, the_num);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					fav = new BoardFavVO();
					fav.setFav_num(rs.getInt("fav_num"));
					fav.setThe_num(rs.getInt("the_num"));
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
		public List<MusicVO> getListBoardFav(int start,int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<MusicVO> list = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT * FROM theme_board b JOIN member m USING(mem_num) "
						+ "JOIN board_fav f USING(the_num) "
						+ "JOIN music m USING(the_num) "
						+ "WHERE f.mem_num=? "
						+ "ORDER BY the_num DESC)a) WHERE rnum >= ? AND rnum<=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, mem_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<MusicVO>();
				while(rs.next()) {
					MusicVO board = new MusicVO();
					board.setMus_num(rs.getInt("mus_num"));
					board.setMus_title(rs.getString("mus_title"));
					board.setMus_singer(rs.getString("mus_singer"));
					board.setMus_album(rs.getString("mus_album"));
					board.setMus_date(rs.getString("mus_date"));
					board.setThe_num(rs.getInt("the_num"));
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
	public void insertReplyBoard(ThemeBoardReVO boardReply)
	                                 throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO theme_comment (treply_num,"
				+ "the_num,treply_content,treply_date,mem_num) "
				+ "VALUES (board_comment.nextval,?,?,SYSDATE,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, boardReply.getThe_num());
			pstmt.setString(2, boardReply.getTreply_content());
			pstmt.setInt(3, boardReply.getMem_num());
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
		public int getReplyBoardCount(int the_num)
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
				sql = "SELECT COUNT(*) FROM theme_comment b "
					+ "JOIN member m ON b.mem_num=m.mem_num "
					+ "WHERE b.the_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, the_num);
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
		public List<ThemeBoardReVO> getListReplyBoard(int start,
				                int end, int the_num)
		                                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ThemeBoardReVO> list = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM theme_comment b "
					+ "JOIN member m USING (mem_num) "
					+ "WHERE b.the_num=? ORDER BY b.treply_num "
					+ "DESC)a) WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, the_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<ThemeBoardReVO>();
				while(rs.next()) {
					ThemeBoardReVO reply = new ThemeBoardReVO();
					reply.setTreply_num(rs.getInt("treply_num"));
					//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
					reply.setTreply_date(
						DurationFromNow.getTimeDiffLabel(
									rs.getString("treply_date")));
					if(rs.getString("treply_modify_date")!=null) {
						reply.setTreply_modify_date(
						 DurationFromNow.getTimeDiffLabel(
								  rs.getString("treply_modify_date")));
					}
					reply.setTreply_content(
							StringUtil.useBrNoHtml(
								    rs.getString("treply_content")));
					reply.setThe_num(rs.getInt("The_num"));
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
		public ThemeBoardReVO getReplyBoard(int treply_num)
		                                   throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ThemeBoardReVO reply = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM theme_comment WHERE treply_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, treply_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					reply = new ThemeBoardReVO();
					reply.setTreply_num(rs.getInt("treply_num"));
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
		public void updateReplyBoard(ThemeBoardReVO reply)
		                                       throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE theme_comment SET treply_content=?,"
					+ "treply_modify_date=SYSDATE WHERE treply_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setString(1, reply.getTreply_content());
				pstmt.setInt(2, reply.getTreply_num());
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
		public void deleteReplyBoard(int treply_num)
				                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM theme_comment WHERE treply_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, treply_num);
				//SQL문 실행
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//한건의 게시글의 앞 뒤 게시글 알아내기
		public int[] getPreOrNextBoard(int the_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int[] numArray = new int[2];
			
			try {
				conn = DBUtil.getConnection();
				sql = "select lag, lead from (select the_num,lag(the_num,1,0) over (order by the_num) as lag, lead(the_num,1,0) over (order by the_num) as lead from theme_board) where the_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, the_num);
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
		
}