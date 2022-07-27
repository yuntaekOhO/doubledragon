package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class FreeWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.jsp";
		}
		
		//로그인이 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		FreeBoardVO board = new FreeBoardVO();
		board.setFree_title(multi.getParameter("free_title"));
		board.setFree_content(multi.getParameter("free_content"));
		board.setFree_img(multi.getFilesystemName("free_img"));
		board.setFree_code(Integer.parseInt(multi.getParameter("free_code")));
		board.setMem_num(user_num);
		
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		dao.insertBoard(board);
		
		return "/WEB-INF/views/board/freeWrite.jsp";
	}

}
