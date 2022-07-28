package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.music.vo.MusicVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class ThemeUpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		int the_num = Integer.parseInt(
				        request.getParameter("the_num"));
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		
		ThemeBoardVO board = dao.getBoard(the_num);
		MusicVO music = dao.getMusic(the_num);
		
		if(user_num != board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/noticeList.jsp";
		}
		
		//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호 일치
		request.setAttribute("board", board);
		request.setAttribute("music", music);
		
		return "/WEB-INF/views/board/themeUpdateForm.jsp";
	}

}
