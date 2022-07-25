package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class NoticeListAction implements Action{
//공지사항 메인
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
		int count = dao.getNoticeCount(keyfield, keyword);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 20,10,"noticeList.do");
		
		List<NoticeBoardVO> list = null;
		if(count > 0) {
			list = dao.getListNoticeBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/board/noticeList.jsp";
	}

}
