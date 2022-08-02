package kr.main.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.FreeBoardDAO;
import kr.board.dao.InquiryBoardDAO;
import kr.board.dao.NoticeBoardDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.board.vo.InquiryBoardVO;
import kr.board.vo.NoticeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.main.dao.MainSearchDAO;
import kr.main.vo.MainVO;
import kr.music.vo.MusicVO;
import kr.util.PagingUtil;

public class SearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		String keyword = request.getParameter("keyword");
		
		MainSearchDAO dao = MainSearchDAO.getInstance();
		int count = dao.getSearchCount(keyword);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,"/main/searchList.do");
		
		List<MainVO> list = null;
		if(count>0) {
			list = dao.getSearchList(page.getStartRow(), page.getEndRow(), keyword);

			request.setAttribute("list", list);
		}
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/main/searchList.jsp";
	}

}
