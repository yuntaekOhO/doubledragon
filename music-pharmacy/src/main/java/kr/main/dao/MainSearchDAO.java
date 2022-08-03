package kr.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.dao.FreeBoardDAO;
import kr.board.dao.InquiryBoardDAO;
import kr.board.dao.NoticeBoardDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.board.vo.InquiryBoardVO;
import kr.board.vo.NoticeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.main.vo.MainVO;
import kr.music.vo.MusicVO;
import kr.util.DBUtil;

public class MainSearchDAO {
	//싱글턴 패턴
	private static MainSearchDAO instance = new MainSearchDAO();
	
	public static MainSearchDAO getInstance() {
		return instance;
	}
	private MainSearchDAO() {}
	
	private static String[] tableArr = {"free_board ",
			  "theme_board ",
			  "music ",
			  "inquiry_board ",
			  "notice_board "};

	private static String[] keyArr = {"free_title || free_content",
			"the_title || the_content",
			"mus_title || mus_singer",
			"inq_question || inq_answer",
			"not_title || not_content"};
	
	//레코드 수
	public int getSearchCount(String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		String sql = "";
		String sub_sql = "";

		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			if(keyword!=null&&!"".equals(keyword)) {
				for(int i=0;i<keyArr.length;i++) {
					sub_sql = "WHERE " + keyArr[i] + " LIKE ?";
					
					sql = "SELECT COUNT(*) FROM " + tableArr[i] + sub_sql;
					
					if(i==0) {
						pstmt0 = conn.prepareStatement(sql);
						pstmt0.setString(1, "%"+keyword+"%");
						rs = pstmt0.executeQuery();
						if(rs.next()) {
							count += rs.getInt(1);
						}
					}else if(i==1) {
						pstmt1 = conn.prepareStatement(sql);
						pstmt1.setString(1, "%"+keyword+"%");
						rs = pstmt1.executeQuery();
						if(rs.next()) {
							count += rs.getInt(1);
						}
					}else if(i==2) {
						pstmt2 = conn.prepareStatement(sql);
						pstmt2.setString(1, "%"+keyword+"%");
						rs = pstmt2.executeQuery();
						if(rs.next()) {
							count += rs.getInt(1);
						}
					}else if(i==3) {
						pstmt3 = conn.prepareStatement(sql);
						pstmt3.setString(1, "%"+keyword+"%");
						rs = pstmt3.executeQuery();
						if(rs.next()) {
							count += rs.getInt(1);
						}
					}else if(i==4) {
						pstmt4 = conn.prepareStatement(sql);
						pstmt4.setString(1, "%"+keyword+"%");
						rs = pstmt4.executeQuery();
						if(rs.next()) {
							count += rs.getInt(1);
						}
					}
					
				}//end of for
			}//end of if keyword 
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, null);
			DBUtil.executeClose(rs, pstmt0, conn);
		}
		return count;
	}
	
	//통합검색 목록
	public List<MainVO> getSearchList(int start, int end, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		List<MainVO> list = null;
		MainVO result = null;
		String[] numArr = {" free_num ",
							 " the_num ",
							 " mus_num, the_num ",
							 " inq_num ",
							 " not_num "};
		
		String sql = "";
		String sub_sql = "";
		
		FreeBoardDAO fdao = FreeBoardDAO.getInstance();
		ThemeBoardDAO tdao = ThemeBoardDAO.getInstance();
		NoticeBoardDAO ndao = NoticeBoardDAO.getInstance();
		InquiryBoardDAO idao = InquiryBoardDAO.getInstance();
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = new ArrayList<MainVO>();
			
			if(keyword!=null&&!"".equals(keyword)) {
				for(int i=0;i<keyArr.length;i++) {
					sub_sql = "WHERE " + keyArr[i] + " LIKE ?";
					
					sql = "SELECT" + numArr[i] + "FROM " + tableArr[i] + sub_sql + " ORDER BY" + numArr[i];
					
					if(i==0) {
						pstmt0 = conn.prepareStatement(sql);
						pstmt0.setString(1, "%"+keyword+"%");
						rs = pstmt0.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							FreeBoardVO fvo = fdao.getBoard(rs.getInt("free_num"));
							result.setFree(fvo);
							list.add(result);
						}
					}else if(i==1) {
						pstmt1 = conn.prepareStatement(sql);
						pstmt1.setString(1, "%"+keyword+"%");
						rs = pstmt1.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							ThemeBoardVO tvo = tdao.getBoard(rs.getInt("the_num"));
							MusicVO mvo = tdao.getMusic(rs.getInt("the_num"));
							result.setThe(tvo);
							result.setMus(mvo);
							list.add(result);
						}
					}else if(i==2) {
						pstmt2 = conn.prepareStatement(sql);
						pstmt2.setString(1, "%"+keyword+"%");
						rs = pstmt2.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							MusicVO mvo = tdao.getMusic(rs.getInt("the_num"));
							ThemeBoardVO tvo = tdao.getBoard(rs.getInt("the_num"));
							result.setMus(mvo);
							result.setThe(tvo);
							list.add(result);
						}
					}else if(i==3) {
						pstmt3 = conn.prepareStatement(sql);
						pstmt3.setString(1, "%"+keyword+"%");
						rs = pstmt3.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							InquiryBoardVO ivo = idao.getBoard(rs.getInt("inq_num"));
							result.setInq(ivo);
							list.add(result);
						}
					}else if(i==4) {
						pstmt4 = conn.prepareStatement(sql);
						pstmt4.setString(1, "%"+keyword+"%");
						rs = pstmt4.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							NoticeBoardVO nvo = ndao.getBoard(rs.getInt("not_num"));
							result.setNotice(nvo);
							list.add(result);
						}
					}
					
				}//end of for
			}//end of if keyword
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, null);
			DBUtil.executeClose(rs, pstmt0, conn);
		}
		
		return list;
	}
}
