package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		MemberVO member = new MemberVO();
		member.setMem_num(user_num);
		member.setName(request.getParameter("name"));
		member.setNick(request.getParameter("nick"));
		member.setEmail(request.getParameter("email"));
		member.setCell(request.getParameter("cell"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddr1(request.getParameter("addr1"));
		member.setAddr2(request.getParameter("addr2"));
		member.setBirthday(request.getParameter("birthday"));
		member.setMusic(request.getParameter("music"));
		member.setRoute(request.getParameter("route"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
		
		return "/WEB-INF/views/member/modifyUser.jsp";
	}

}
