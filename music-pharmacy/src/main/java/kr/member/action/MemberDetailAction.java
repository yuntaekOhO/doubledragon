package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;

public class MemberDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.jsp";
		}
		if(user_auth<3) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setCharacterEncoding("utf-8");
		
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int auth = Integer.parseInt(request.getParameter("auth"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMemberByAdmin(auth, mem_num);
		
		request.setAttribute("mem_num", mem_num);
		
		return "/WEB-INF/views/member/memberDetail.jsp";
	}

}
