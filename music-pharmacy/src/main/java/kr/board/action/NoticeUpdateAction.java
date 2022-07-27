package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class NoticeUpdateAction implements Action {
public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int not_num = Integer.parseInt(multi.getParameter("not_num"));
		String filename = multi.getFilesystemName("not_img");
		
		NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
		//수정전 데이터
		NoticeBoardVO db_board = dao.getBoard(not_num);
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			
			//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, filename);
			return "/WEB-INF/views/common/noticeList.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		NoticeBoardVO board = new NoticeBoardVO();
		board.setNot_num(not_num);
		board.setNot_title(multi.getParameter("not_title"));
		board.setNot_content(multi.getParameter("not_content"));
		board.setNot_img(filename);
		board.setMem_num(user_num);
		
		dao.updateBoard(board);
		
		if(filename!=null) {
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_board.getNot_img());
		}
		
		return "redirect:/board/detail.do?not_num="+not_num;
	}
}
