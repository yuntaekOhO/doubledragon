package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class FreeDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int free_num = Integer.parseInt(
				        request.getParameter("free_num"));
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		FreeBoardVO db_board = dao.getBoard(free_num);
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		if(user_num!=null && user_auth==3) {
			return "redirect:/board/freeBoard.do";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		dao.deleteBoard(free_num);
		//파일 삭제
		FileUtil.removeFile(request, db_board.getFree_img());
		
		return "redirect:/board/freeBoard.do";
	}

}
