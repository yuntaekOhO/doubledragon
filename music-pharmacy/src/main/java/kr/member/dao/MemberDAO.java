package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//회원가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; 
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "SELECT member_seq.nextval FROM dual";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			sql = "INSERT INTO member (mem_num, id) VALUES (?,?)";
			
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt2.setInt(1, num);
			pstmt2.setString(2, member.getId());
			
			pstmt2.executeUpdate();
			
			sql ="INSERT INTO member_detail (mem_num,name,nick,passwd,cell,email,zipcode,addr1,addr2,music,route,birthday) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt3 = conn.prepareStatement(sql);
			
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getNick());
			pstmt3.setString(4, member.getPasswd());
			pstmt3.setString(5, member.getCell());
			pstmt3.setString(6, member.getEmail());
			pstmt3.setString(7, member.getZipcode());
			pstmt3.setString(8, member.getAddr1());
			pstmt3.setString(9, member.getAddr2());
			pstmt3.setString(10, member.getMusic());
			pstmt3.setString(11, member.getRoute());
			pstmt3.setString(12, member.getBirthday());
			
			pstmt3.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt3, conn);
			DBUtil.executeClose(rs, pstmt2, conn);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//Id 중복체크 로그인처리
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.id=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setPhoto(rs.getString("photo"));
				member.setEmail(rs.getString("email"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	
	//마이페이지
	public MemberVO getMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setNick(rs.getString("nick"));
				member.setCell(rs.getString("cell"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddr1(rs.getString("addr1"));
				member.setAddr2(rs.getString("addr2"));
				member.setPhoto(rs.getString("photo"));
				member.setMusic(rs.getString("music"));
				member.setRoute(rs.getString("route"));
				member.setBirthday(rs.getString("birthday"));
				member.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	
	//회원 수
	public int getMemberCountByAdmin(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE d.nick LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE m.auth LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM member m LEFT OUTER JOIN member_detail d USING (mem_num) " + sub_sql;
			
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null&&!"".equals(keyword)) {
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
	//회원 목록
	public List<MemberVO> getListMemberByAdmin(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE m.id LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE d.nick LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE m.auth LIKE ?";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM member m LEFT OUTER JOIN member_detail d USING(mem_num) " + sub_sql
					+ " ORDER BY mem_num DESC NULLS LAST)a) WHERE rnum>=? AND rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null&&!"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MemberVO>();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setNick(rs.getString("nick"));
				member.setName(rs.getString("name"));
				member.setPasswd(rs.getString("passwd"));
				member.setCell(rs.getString("cell"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddr1(rs.getString("addr1"));
				member.setAddr2(rs.getString("addr2"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setMusic(rs.getString("music"));
				member.setBirthday(rs.getString("birthday"));
				member.setRoute(rs.getString("route"));
				member.setPhoto(rs.getString("photo"));
				list.add(member);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//회원정보수정
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE member_detail SET name=?,cell=?,email=?,zipcode=?,addr1=?,addr2=?,birthday=?,music=? WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getCell());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddr1());
			pstmt.setString(6, member.getAddr2());
			pstmt.setString(7, member.getBirthday());
			pstmt.setString(8, member.getMusic());
			pstmt.setInt(9, member.getMem_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//비밀번호수정
	public void updatePassword(String passwd,int mem_num)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE member_detail SET passwd=? WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, passwd);
			pstmt.setInt(2, mem_num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//프로필 사진 수정
	public void updateMyPhoto(String photo, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE member_detail SET photo=? WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, photo);
			pstmt.setInt(2, mem_num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//탈퇴
	public void deleteMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			sql = "UPDATE member SET auth=0 WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.executeUpdate();
			
			sql = "DELETE FROM member_detail WHERE mem_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, mem_num);
			pstmt2.executeUpdate();
			
			conn.commit();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, conn);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
