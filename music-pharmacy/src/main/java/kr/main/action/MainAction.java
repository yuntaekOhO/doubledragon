package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.NoticeBoardDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ThemeBoardDAO themeBoardDao = ThemeBoardDAO.getInstance();
		List<ThemeBoardVO> themeBoardList = themeBoardDao.getListBoard(1, 6, null, null);
		
		NoticeBoardDAO noticeBoardDao = NoticeBoardDAO.getInstance();
		List<NoticeBoardVO> noticeBoardList = noticeBoardDao.getListNoticeBoard(1, 5, null, null);
		
		request.setAttribute("notice", noticeBoardList);
		request.setAttribute("theme", themeBoardList);
		
		return "/WEB-INF/views/main/main.jsp";
	}

}
