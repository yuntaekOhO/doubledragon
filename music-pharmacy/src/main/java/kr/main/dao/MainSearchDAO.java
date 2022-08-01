package kr.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.main.vo.MainVO;
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
							 " mus_num ",
							 " inq_num ",
							 " not_num "};
		
		String sql = "";
		String sub_sql = "";
		
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
							result.setFree_num(rs.getInt("free_num"));
							list.add(result);
							if(result!=null) {
								System.out.println("자유 조회");
							}
						}
					}else if(i==1) {
						pstmt1 = conn.prepareStatement(sql);
						pstmt1.setString(1, "%"+keyword+"%");
						rs = pstmt1.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							result.setThe_num(rs.getInt("the_num"));
							list.add(result);
							if(result!=null) {
								System.out.println("테마 조회");
							}
						}
					}else if(i==2) {
						pstmt2 = conn.prepareStatement(sql);
						pstmt2.setString(1, "%"+keyword+"%");
						rs = pstmt2.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							result.setMus_num(rs.getInt("mus_num"));
							list.add(result);
							if(result!=null) {
								System.out.println("음악 조회");
							}
						}
					}else if(i==3) {
						pstmt3 = conn.prepareStatement(sql);
						pstmt3.setString(1, "%"+keyword+"%");
						rs = pstmt3.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							result.setInq_num(rs.getInt("inq_num"));
							list.add(result);
							if(result!=null) {
								System.out.println("질답 조회");
							}
						}
					}else if(i==4) {
						pstmt4 = conn.prepareStatement(sql);
						pstmt4.setString(1, "%"+keyword+"%");
						rs = pstmt4.executeQuery();
						while(rs.next()) {
							result = new MainVO();
							result.setNot_num(rs.getInt("not_num"));
							list.add(result);
							if(result!=null) {
								System.out.println("공지 조회");
							}
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
