package kr.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.MyWriteDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardReVO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PagingUtil;

public class MyWritedReplyListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		if(user_num==null) {//로그인 안된 경우
			return "redirect:/member/loginForm.jsp";
		}
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO member = mDao.getMember(user_num);
		if(user_num!=member.getMem_num()) {//로그인한 회원번호와 db에 저장된 회원번호가 다른 경우
			return "redirect:/main/main.jsp";
		}
		//로그인 된 경우
		ThemeBoardDAO tDao = ThemeBoardDAO.getInstance();
		MyWriteDAO wDao = MyWriteDAO.getInstance();
		//내가 쓴 댓글 갯수
		int theCnt = wDao.getMyReplyBoardCount(user_num);
		//페이지 처리
		PagingUtil tpage = new PagingUtil(Integer.parseInt(pageNum),theCnt,5,5,"myWritedReplyList.do");
		List<ThemeBoardReVO> trelist = null;
		
		//내가 쓴 댓글 목록
		if(theCnt>0) {
			trelist = wDao.getReplyListByMemNum(tpage.getStartRow(),tpage.getEndRow(),user_num);
		}
		
		//내가 쓴 댓글이 달린 글 제목 알아내기
		List<ThemeBoardVO> tBoardList = new ArrayList<ThemeBoardVO>();
		for(ThemeBoardReVO treBoard : trelist) {
			tBoardList.add(tDao.getBoard(treBoard.getThe_num()));
		}
		
		request.setAttribute("theCnt", theCnt);
		request.setAttribute("trelist", trelist);
		request.setAttribute("tBoardList", tBoardList);
		request.setAttribute("tpage", tpage.getPage());
		
		return "/WEB-INF/views/member/myWritedReplyList.jsp";
	}

}
