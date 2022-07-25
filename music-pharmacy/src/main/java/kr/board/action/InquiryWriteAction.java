package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.InquiryBoardDAO;
import kr.board.vo.InquiryBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class InquiryWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null || auth < 3) {
			return "redirect:/member/loginForm.do";
		}
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO member = memberDAO.getMember(user_num);
		
		
		InquiryBoardVO board = new InquiryBoardVO();
		board.setInq_title(request.getParameter("title"));
		board.setInq_writer(member.getNick());
		board.setInq_question(request.getParameter("question"));
		board.setInq_answer(request.getParameter("answer"));
		board.setMem_num(user_num);
		InquiryBoardDAO dao = InquiryBoardDAO.getInstance();
		dao.insertInquiryBoard(board);
		
		System.out.println("글작성 성공");
		
		return "/WEB-INF/views/board/inquiryBoard.jsp";
	}

}
