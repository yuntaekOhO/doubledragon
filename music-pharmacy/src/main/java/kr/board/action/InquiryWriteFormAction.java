package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class InquiryWriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null||auth<3) {//로그인이 되어있지 않거나 관리자가 아닌 경우
			//*url 변경할 가능성 있음
			return "redirect:/member/loginForm.do";
		}
		//로그인이 관리자 권한으로 된 경우
		request.setAttribute("user_num", user_num);
		request.setAttribute("auth", auth);
		return "/WEB-INF/views/board/inquiryWriteForm.jsp";
	}

}
