package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.StringUtil;

public class NoticeDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//글번호 반환
		int not_num = Integer.parseInt(request.getParameter("not_num"));
		NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(not_num);
		//글상세 정보 반환
		NoticeBoardVO board = dao.getBoard(not_num); 
		//HTML을 허용하지 않음
		board.setNot_title(StringUtil.useNoHtml(board.getNot_title()));
		//HTML을 허용하지 않으면서 줄바꿈 처리
		board.setNot_content(StringUtil.useBrNoHtml(board.getNot_content()));
		
		int mem_num = board.getMem_num();
		
		MemberDAO memberDao = MemberDAO.getInstance();
		MemberVO member = memberDao.getMember(mem_num);
		
		int[] arr = null;
		//이전 글, 다음 글 번호 저장
		arr = dao.getPreOrNextBoard(not_num);
		
		//이전,다음글 정보 반환
		NoticeBoardVO preBoard = dao.getBoard(arr[0]);
		if(preBoard!=null) {
			preBoard.setNot_title(StringUtil.shortWords(15,preBoard.getNot_title()));
		}
		NoticeBoardVO nextBoard = dao.getBoard(arr[1]);
		if(nextBoard!=null) {
			nextBoard.setNot_title(StringUtil.shortWords(15,nextBoard.getNot_title()));
		}
		
		request.setAttribute("board", board);
		request.setAttribute("member", member);
		request.setAttribute("pre_board", preBoard);
		request.setAttribute("next_board", nextBoard);
		
		return "/WEB-INF/views/board/noticeDetail.jsp";
	}

}
