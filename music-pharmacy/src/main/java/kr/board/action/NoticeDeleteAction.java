package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.NoticeBoardDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class NoticeDeleteAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int not_num = Integer.parseInt(
				        request.getParameter("not_num"));
		NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
		NoticeBoardVO db_board = dao.getBoard(not_num);
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/noticeList.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		dao.deleteBoard(not_num);
		//파일 삭제
		FileUtil.removeFile(request, db_board.getNot_img());
		
		return "redirect:/board/noticeList.do";
	}
}
