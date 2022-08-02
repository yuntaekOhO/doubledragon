package kr.member.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberPointVO;
import kr.member.vo.MemberVO;


public class MyPointAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		
		List<MemberPointVO> list = null;
		list = dao.getdateByMemNum(user_num);
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("member", member);
		
		
		return "/WEB-INF/views/member/myPoint.jsp";
	}

}
