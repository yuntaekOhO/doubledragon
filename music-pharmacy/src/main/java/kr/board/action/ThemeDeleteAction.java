package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.music.vo.MusicVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class ThemeDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int the_num = Integer.parseInt(
				        request.getParameter("the_num"));
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		ThemeBoardVO db_board = dao.getBoard(the_num);
		MusicVO music = dao.getMusic(the_num);
		if(user_auth==3) {
			dao.deleteBoard(db_board.getThe_num());
			System.out.println("글 삭제 완료");
			return "redirect:/board/themeBoard.do";
		}else if(user_num != db_board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		dao.deleteBoard(the_num);
		//파일 삭제
		FileUtil.removeFile(request, db_board.getThe_img());
		FileUtil.removeFile(request, music.getMus_img());
		return "redirect:/board/themeBoard.do";
	}

}
