package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.FreeBoardDAO;
import kr.board.dao.MyWriteDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PagingUtil;

public class MyWritedListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인 안된 경우
			return "redirect:/member/loginForm.jsp";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		MemberDAO dao = MemberDAO.getInstance();
		FreeBoardDAO fDAO = FreeBoardDAO.getInstance();
		ThemeBoardDAO tDAO = ThemeBoardDAO.getInstance();
		MyWriteDAO wDAO = MyWriteDAO.getInstance();
		
		MemberVO member = dao.getMember(user_num);
		
		String fkeyfield = "3";
		String tkeyfield = "2";
		
		String keyword = member.getNick();
		
		//저잣거리,동의보감 로그인한 회원번호와 일치하는 id로 글의 갯수 반환 
		int freeCnt = fDAO.getBoardCount(fkeyfield, keyword);
		int theCnt = tDAO.getBoardCount(tkeyfield, keyword);
		//페이지처리
		PagingUtil fpage = new PagingUtil(fkeyfield,keyword,Integer.parseInt(pageNum),freeCnt,10,10,"myWritedList.do");
		PagingUtil tpage = new PagingUtil(tkeyfield,keyword,Integer.parseInt(pageNum),theCnt,10,10,"myWritedList.do");
		
		List<FreeBoardVO> flist = null;
		List<ThemeBoardVO> tlist = null;
		
		//회원 id로 저잣거리,동의보감 게시글 검색해서 나온 결과를 list에 담음
		if(freeCnt>0) {
			flist = fDAO.getListFreeBoard(fpage.getStartRow(), fpage.getEndRow(), fkeyfield, keyword);
		}
		if(theCnt>0) {
			tlist = tDAO.getListBoard(tpage.getStartRow(), tpage.getEndRow(), tkeyfield, keyword);
		}
	
		request.setAttribute("freeCnt", freeCnt);
		request.setAttribute("theCnt", theCnt);
		//if(flist!=null) {
			request.setAttribute("flist", flist);
		//}
		//if(tlist!=null) {
			request.setAttribute("tlist", tlist);
		//}
		request.setAttribute("fpage", fpage.getPage());
		request.setAttribute("tpage", tpage.getPage());
		
		return "/WEB-INF/views/member/myWritedList.jsp";
	}

}
