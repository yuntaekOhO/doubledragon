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
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}else if(user_num!=null&&user_auth<3) {
			return "redirect:/board/inquiryBoard.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int inq_num = Integer.parseInt(multi.getParameter("inq_num"));
		String inq_img = multi.getFilesystemName("inq_img");
		
		InquiryBoardDAO dao = InquiryBoardDAO.getInstance();
		InquiryBoardVO db_board = dao.getBoard(user_num);
		
		if(user_auth<3) {
			//관리자가 아니면
			//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, inq_img);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 글 작성 회원번호가 같음
		InquiryBoardVO board = new InquiryBoardVO();
		board.setInq_num(inq_num);
		board.setInq_question(multi.getParameter("inq_question"));
		board.setInq_answer(multi.getParameter("inq_answer"));
		board.setInq_img(inq_img);
		board.setMem_num(user_num);
		
		dao.updateInqBoard(board);
		
		if(inq_img!=null) {
			//수정시 원래 파일 삭제
			FileUtil.removeFile(request, db_board.getInq_img());
		}
		
		return "redirect:/board/inqDetail.do?inq_num="+inq_num;
	}

}
