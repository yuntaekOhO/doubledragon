package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.InquiryBoardDAO;
import kr.board.vo.InquiryBoardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class InquiryBoardAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		InquiryBoardDAO dao = InquiryBoardDAO.getInstance();
		int count = dao.getInqBoardCount(keyfield, keyword);
		
		//페이지처리
		PagingUtil page = new PagingUtil(keyfield,keyword,Integer.parseInt(pageNum),count,20,10,"inquiryBoard.do");
		
		List<InquiryBoardVO> list = null;
		
		if(count>0) {
			list = dao.selectList(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/board/inquiryBoard.jsp";
	}

}
