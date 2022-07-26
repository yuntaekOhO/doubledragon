package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class FreeBoardAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		int count = dao.getBoardCount(keyfield, keyword);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,
				Integer.parseInt(pageNum),count,20,10,"freeBoard.do");
		
		List<FreeBoardVO> list = null;
		if(count > 0) {
			list = dao.getListBoard(page.getStartRow(),
					       page.getEndRow(), keyfield, keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return  "/WEB-INF/views/board/freeBoard.jsp";
	}

}
