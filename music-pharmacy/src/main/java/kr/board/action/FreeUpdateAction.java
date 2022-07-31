package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class FreeUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		MultipartRequest multi = 
				FileUtil.createFile(request);
		int free_num = Integer.parseInt(multi.getParameter("free_num"));
		String free_img = multi.getFilesystemName("free_img");
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		//수정전 데이터
		FreeBoardVO db_board = dao.getBoard(free_num);
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			
			//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, free_img);
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원번호가 일치
		FreeBoardVO board = new FreeBoardVO();
		board.setFree_num(free_num);
		board.setFree_title(multi.getParameter("free_title"));
		board.setFree_content(multi.getParameter("free_content"));
		board.setFree_img(free_img);
		board.setFree_code(Integer.parseInt(multi.getParameter("free_code")));
		board.setMem_num(user_num);
		
		dao.updateBoard(board);
		//새 파일로 교체할 때 원래 파일 제거
		FileUtil.removeFile(request, 
				        db_board.getFree_img());
	
		return "redirect:/board/freeDetail.do?free_num="+free_num;
	}

}
