package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
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
				int mem_num = board.getMem_num();
			
				MemberDAO memberDao = MemberDAO.getInstance();
				MemberVO member = memberDao.getMember(mem_num);
				
				//HTML를 허용하지 않음
				board.setFree_title(StringUtil.useNoHtml(
						                    board.getFree_title()));
				//HTML를 허용하지 않으면서 줄바꿈 처리
				board.setFree_content(StringUtil.useBrNoHtml(
						                   board.getFree_content()));
				
				request.setAttribute("board", board);
				
				//이전 글, 다음 글 번호 저장
				int[] arr = null;
				arr = dao.getPreOrNextBoard(free_num);
				
				//이전,다음글 정보 반환
				FreeBoardVO preBoard = dao.getBoard(arr[0]);
				if(preBoard!=null) {
					preBoard.setFree_title(StringUtil.shortWords(15,preBoard.getFree_title()));
				}
				FreeBoardVO nextBoard = dao.getBoard(arr[1]);
				if(nextBoard!=null) {
					nextBoard.setFree_title(StringUtil.shortWords(15,nextBoard.getFree_title()));
				}
				
				
				request.setAttribute("board", board);
				request.setAttribute("member", member);
				request.setAttribute("pre_board", preBoard);
				request.setAttribute("next_board", nextBoard);
				
			
				return "/WEB-INF/views/board/freeDetail.jsp";
	}

}
