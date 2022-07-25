package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class NoticeWriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		  HttpSession session = request.getSession();
		  Integer user_num = (Integer)session.getAttribute("user_num");
		  Integer auth = (Integer)session.getAttribute("user_auth");
		  
		  if(auth < 3) { //관리자가 아닌경우
			  return "redirect:/member/noticeList.do"; 
		  }

		return "/WEB-INF/views/board/noticeWriteForm.jsp";

	}

}
