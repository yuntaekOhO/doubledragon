package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
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
		NoticeBoardVO board = dao.getNoticeBoard(not_num); //얘때문에 글상세정보 필요
		//HTML을 허용하지 않음
		board.setNot_title(StringUtil.useNoHtml(board.getNot_title()));
		//HTML을 허용하지 않으면서 줄바꿈 처리
		board.setNot_content(StringUtil.useBrNoHtml(board.getNot_content()));
		
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/board/noticeDetail.jsp";
	}

}
