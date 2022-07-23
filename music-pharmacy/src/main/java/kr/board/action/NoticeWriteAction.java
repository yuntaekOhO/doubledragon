package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;


public class NoticeWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인기능 만들어지면 다시
		/*
		 * HttpSession session = request.getSession(); Integer user_num =
		 * (Integer)session.getAttribute("user_num"); if(user_num == null) { //로그인이 되지
		 * 않은 경우 return "redirect:/member/loginForm.jsp"; }
		 */
		
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		NoticeBoardVO board = new NoticeBoardVO();
		board.setNot_title(multi.getParameter("not_title"));
		board.setNot_content(multi.getParameter("not_content"));
		board.setNot_img(multi.getFilesystemName("not_img"));
//		board.setNot_num(user_num);
		
		NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
		dao.insertBoard(board);
		
		return "/WEB-INF/views/board/noticeWrite.jsp";
	}

}
