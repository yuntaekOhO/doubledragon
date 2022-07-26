package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;


import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;
import kr.util.StringUtil;

public class ThemeUpdateAction implements Action {

public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int the_num = Integer.parseInt(multi.getParameter("the_num"));
		String filename = multi.getFilesystemName("the_img");
		
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		//수정전 데이터
		ThemeBoardVO db_board = dao.getBoard(the_num);
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			
			//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, filename);
			return "/WEB-INF/views/common/noticeList.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		ThemeBoardVO board = new ThemeBoardVO();
		board.setThe_num(the_num);
		board.setThe_title(multi.getParameter("the_title"));
		board.setThe_content(multi.getParameter("the_content"));
		board.setThe_img(filename);
		board.setThe_code(Integer.parseInt(multi.getParameter("the_code")));
		board.setThe_video(multi.getParameter("the_video"));
		board.setThe_url(multi.getParameter("the_url"));
		board.setMem_num(user_num);
		
		dao.updateBoard(board);
		
		if(filename!=null) {
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_board.getThe_img());
		}
		
		return "redirect:/board/themeDetail.do?the_num="+the_num;
	}

}
