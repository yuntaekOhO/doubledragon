package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;


public class NoticeWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인기능 만들어지면 다시
		
		 HttpSession session = request.getSession(); 
		 Integer user_num =(Integer)session.getAttribute("user_num"); 
		 	if(user_num == null) { //로그인이 되지않은 경우 
		 		return "redirect:/member/loginForm.do"; 
		 
		 }
	
		 
		
		//로그인 된 경우
		request.setCharacterEncoding("utf-8");
		 	
		MultipartRequest multi = FileUtil.createFile(request);
		
		//이거왜한거에여??
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO member = memberDAO.getMember(user_num);
		
		NoticeBoardVO board = new NoticeBoardVO();
		
		board.setNot_title(multi.getParameter("not_title"));
		board.setNot_writer(member.getNick());
		board.setNot_content(multi.getParameter("not_content"));
		board.setNot_img(multi.getFilesystemName("not_img"));
		board.setMem_num(user_num);
		
		NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
		dao.insertBoard(board);
		
		return "/WEB-INF/views/board/noticeWrite.jsp";
	}

}
