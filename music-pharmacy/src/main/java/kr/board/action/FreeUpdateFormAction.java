package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;

public class FreeUpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		int free_num = Integer.parseInt(
				        request.getParameter("free_num"));
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		FreeBoardVO board = dao.getBoard(free_num);
		if(mem_num != board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호 일치
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/board/freeUpdateForm.jsp";
	}

}