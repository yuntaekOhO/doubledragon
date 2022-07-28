package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.music.vo.MusicVO;
import kr.util.StringUtil;

public class ThemeDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int the_num = Integer.parseInt(request.getParameter("the_num"));
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(the_num);
		//글상세 정보 반환
		ThemeBoardVO board = dao.getBoard(the_num);

		//HTML를 허용하지 않음
		board.setThe_title(StringUtil.useNoHtml(
				board.getThe_title()));
		//HTML를 허용하지 않으면서 줄바꿈 처리
		board.setThe_content(StringUtil.useBrNoHtml(
				board.getThe_content()));

		MusicVO music = dao.getMusic(the_num);
		
		request.setAttribute("board", board);
		request.setAttribute("music", music);

		return "/WEB-INF/views/board/themeDetail.jsp";
	}

}
