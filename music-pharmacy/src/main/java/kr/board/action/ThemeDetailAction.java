package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.music.vo.MusicVO;
import kr.util.StringUtil;

public class ThemeDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int the_num = Integer.parseInt(request.getParameter("the_num"));
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(the_num);
		//글상세 정보 반환
		ThemeBoardVO board = dao.getBoard(the_num);
		int mem_num = board.getMem_num();
		
		MemberDAO memberDao = MemberDAO.getInstance();
		MemberVO member = memberDao.getMember(mem_num);
		
		//HTML를 허용하지 않음
		board.setThe_title(StringUtil.useNoHtml(
				board.getThe_title()));
		//HTML를 허용하지 않으면서 줄바꿈 처리
		board.setThe_content(StringUtil.useBrNoHtml(
				board.getThe_content()));

		MusicVO music = dao.getMusic(the_num);
		
		request.setAttribute("board", board);
		request.setAttribute("music", music);
		
		//이전 글, 다음 글 번호 저장
		int[] arr = null;
		arr = dao.getPreOrNextBoard(the_num);
		
		//이전,다음글 정보 반환
		ThemeBoardVO preBoard = dao.getBoard(arr[0]);
		if(preBoard!=null) {
			preBoard.setThe_title(StringUtil.shortWords(15,preBoard.getThe_title()));
		}
		ThemeBoardVO nextBoard = dao.getBoard(arr[1]);
		if(nextBoard!=null) {
			nextBoard.setThe_title(StringUtil.shortWords(15,nextBoard.getThe_title()));
		}
		
		
		request.setAttribute("board", board);
		request.setAttribute("member", member);
		request.setAttribute("pre_board", preBoard);
		request.setAttribute("next_board", nextBoard);
		
	
		return "/WEB-INF/views/board/themeDetail.jsp";
	}

}
