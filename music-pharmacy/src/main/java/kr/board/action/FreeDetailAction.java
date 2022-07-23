package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class FreeDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
				int free_num = Integer.parseInt(
						        request.getParameter("free_num"));
				FreeBoardDAO dao = FreeBoardDAO.getInstance();
				//조회수 증가
				dao.updateReadcount(free_num);
				//글상세 정보 반환
				FreeBoardVO board = dao.getBoard(free_num);
				
				//HTML를 허용하지 않음
				board.setFree_title(StringUtil.useNoHtml(
						                    board.getFree_title()));
				//HTML를 허용하지 않으면서 줄바꿈 처리
				board.setFree_content(StringUtil.useBrNoHtml(
						                   board.getFree_content()));
				
				request.setAttribute("board", board);
				
				return "/WEB-INF/views/board/freeDetail.jsp";
	}

}
