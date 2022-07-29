package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ThemeBoard1Action implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		int code = 1;
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		int count = dao.getSubBoardCount(keyfield, keyword,code);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 20,10,"themeBoard1.do");
		
		List<ThemeBoardVO> list = null;
		if(count > 0) {
			list = dao.getSubListBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword, code);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("code", code);
		
		return "/WEB-INF/views/board/themeBoard.jsp";
	}

}
