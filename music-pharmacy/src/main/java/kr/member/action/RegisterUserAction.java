package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		MemberVO member = new MemberVO();
		
		member.setId(request.getParameter("id"));
		member.setNick(request.getParameter("nick"));
		member.setName(request.getParameter("name"));
		member.setPasswd(request.getParameter("passwd"));
		member.setCell(request.getParameter("cell"));
		member.setEmail(request.getParameter("email"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddr1(request.getParameter("addr1"));
		member.setAddr2(request.getParameter("addr2"));
		
		String[] musicArr = request.getParameterValues("music");
		String output = "";
		for(int i=0;i<musicArr.length;i++) {
			if(i < musicArr.length-1) {
				output += musicArr[i]+",";
			}else {
				output += musicArr[i];
			}
		}
		member.setMusic(output);
		member.setRoute(request.getParameter("route"));
		member.setBirthday(request.getParameter("birthday"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
