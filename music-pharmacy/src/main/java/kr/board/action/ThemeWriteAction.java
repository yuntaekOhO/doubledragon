package kr.board.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import kr.util.FileUtil;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.ThemeBoardVO;
import kr.music.vo.MusicVO;

public class ThemeWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = 
				FileUtil.createFile(request);
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO member = memberDAO.getMember(user_num);
		
		ThemeBoardVO board = new ThemeBoardVO();
		MusicVO music = new MusicVO();
		
		
		board.setThe_title(multi.getParameter("the_title"));
		board.setThe_writer(member.getNick());
		board.setThe_content(multi.getParameter("the_content"));
		board.setThe_img(
				multi.getFilesystemName("the_img"));
		board.setThe_code(Integer.parseInt(multi.getParameter("the_code")));
		board.setThe_video(multi.getParameter("the_video"));
		board.setThe_url(multi.getParameter("the_url"));
		//board.setMus_genre(multi.getParameter("mus_genre"));
		board.setMem_num(user_num);
	
		
		//music.setThe_num(board.getThe_num());
		music.setMus_album(multi.getParameter("mus_album"));
		music.setMus_singer(multi.getParameter("mus_singer"));
		music.setMus_title(multi.getParameter("mus_title"));
		music.setMus_genre(multi.getParameter("mus_genre"));
		music.setMus_img(multi.getParameter("mus_img"));
		music.setMus_date(multi.getParameter("mus_date"));
		music.setMus_composer(multi.getParameter("mus_composer"));
		music.setMus_songwriter(multi.getParameter("mus_songwriter"));
		
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		dao.insertBoard(board, music);
		
		return "/WEB-INF/views/board/themeWrite.jsp";
	}



}

