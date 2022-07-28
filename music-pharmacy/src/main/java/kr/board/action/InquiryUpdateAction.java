package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.InquiryBoardDAO;
import kr.board.vo.InquiryBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class InquiryUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}else if(user_num!=null&&user_auth<3) {
			return "redirect:/board/inquiryBoard.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int inq_num = Integer.parseInt(multi.getParameter("inq_num"));
		String inq_img = multi.getFilesystemName("inq_img");
		
		InquiryBoardDAO dao = InquiryBoardDAO.getInstance();
		InquiryBoardVO db_board = dao.getBoard(inq_num);
		
		if(user_num!=db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, inq_img);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 글 작성 회원번호가 같음
		InquiryBoardVO board = new InquiryBoardVO();
		board.setInq_num(inq_num);
		board.setInq_writer(multi.getParameter("inq_writer"));
		board.setInq_question(multi.getParameter("inq_question"));
		board.setInq_answer(multi.getParameter("inq_answer"));
		board.setInq_img(inq_img);
		
		dao.updateInqBoard(board);
		
		if(inq_img!=null) {
			FileUtil.removeFile(request, db_board.getInq_img());
		}
		
		return "redirect:/board/inqDetail.do?inq_num="+inq_num;
	}

}
