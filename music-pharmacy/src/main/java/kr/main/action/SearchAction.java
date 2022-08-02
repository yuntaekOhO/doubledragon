package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.main.dao.MainSearchDAO;
import kr.main.vo.MainVO;
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
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,"mainSearch.do");
		
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
