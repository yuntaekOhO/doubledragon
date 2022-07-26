package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.InquiryBoardDAO;
import kr.board.vo.InquiryBoardVO;
import kr.controller.Action;

public class InquiryUpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {//비로그인
			return "redirect:/member/loginForm.do";
		}else if(user_num!=null&&user_auth<3) {//비관리자
			return "redirect:/board/inquiryBoard.do";
		}
		
		int inq_num = Integer.parseInt(request.getParameter("inq_num"));
		InquiryBoardDAO dao = InquiryBoardDAO.getInstance();
		InquiryBoardVO board = dao.getBoard(inq_num);
		
		/*if(user_num!=board.getMem_num()) {
			
		}*/
		
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/board/inquiryUpdateForm.jsp";
	}

}
