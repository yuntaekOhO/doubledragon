package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MemberDetailFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.jsp";
		}
		if(user_auth<3) {
			return "redirect:/WEB-INF/views/common/notice.jsp";
		}
		
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		request.setAttribute("member", member);
		return "/WEB-INF/views/member/memberDetailForm.jsp";
	}

}
