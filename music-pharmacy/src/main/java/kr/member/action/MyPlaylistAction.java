package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.ThemeBoardDAO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.music.vo.MusicVO;
import kr.util.PagingUtil;


public class MyPlaylistAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//String pageNum = request.getParameter("pageNum");
		//if(pageNum==null) pageNum = "1";
		
		//String keyfield = request.getParameter("keyfield");
		//String keyword = request.getParameter("keyword");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		//int count = dao.getBoardCount(keyfield, keyword);
		
		//페이지 처리
		//PagingUtil page = new PagingUtil(keyfield,keyword,Integer.parseInt(pageNum),count,20,10,"myPlaylist.do");
		
		ThemeBoardDAO boardDao = ThemeBoardDAO.getInstance();
		List<MusicVO> boardList = boardDao.getListBoardFav(1, 10, user_num);
		
		MusicVO music = boardDao.getMusicFav(user_num);
		
		request.setAttribute("member", member);
		request.setAttribute("boardList", boardList);
		request.setAttribute("music", music);
		
		return "/WEB-INF/views/member/myPlaylist.jsp";
	}

}
