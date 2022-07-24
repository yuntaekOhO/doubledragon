package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

import com.oreilly.servlet.MultipartRequest;
import kr.util.FileUtil;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;

public class ThemeWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.jsp";
		}
		
		//로그인이 된 경우
		MultipartRequest multi = 
				FileUtil.createFile(request);
		ThemeBoardVO board = new ThemeBoardVO();
		board.setThe_title(multi.getParameter("the_title"));
		board.setThe_writer(multi.getParameter("the_writer"));
		board.setThe_content(multi.getParameter("the_content"));
		board.setThe_img(
				multi.getParameter("the_img"));
		//board.setThe_code(multi.getParameter("the_code"));
		board.setThe_video(multi.getParameter("the_video"));
		board.setThe_url(multi.getParameter("the_url"));
		board.setMus_genre(multi.getParameter("mus_genre"));
		board.setMem_num(user_num);
		
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		dao.insertBoard(board);
		*/
		return "/WEB-INF/views/board/themewrite.jsp";
	}



}

