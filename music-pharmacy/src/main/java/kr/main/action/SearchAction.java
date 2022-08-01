package kr.main.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.FreeBoardDAO;
import kr.board.dao.InquiryBoardDAO;
import kr.board.dao.NoticeBoardDAO;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.board.vo.InquiryBoardVO;
import kr.board.vo.NoticeBoardVO;
import kr.board.vo.ThemeBoardVO;
import kr.controller.Action;
import kr.main.dao.MainSearchDAO;
import kr.main.vo.MainVO;
import kr.music.vo.MusicVO;
import kr.util.PagingUtil;

public class SearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		String keyword = request.getParameter("keyword");
		
		MainSearchDAO dao = MainSearchDAO.getInstance();
		int count = dao.getSearchCount(keyword);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,"searchList.do");
		
		List<MainVO> list = null;
		if(count>0) {
			list = dao.getSearchList(page.getStartRow(), page.getEndRow(), keyword);
		}
		
		FreeBoardDAO fDao = FreeBoardDAO.getInstance();
		FreeBoardVO fBoard = null;
		ThemeBoardDAO tDao = ThemeBoardDAO.getInstance();
		ThemeBoardVO tBoard = null;
		MusicVO music = null;
		InquiryBoardDAO iDao = InquiryBoardDAO.getInstance();
		InquiryBoardVO iBoard = null;
		NoticeBoardDAO nDao = NoticeBoardDAO.getInstance();
		NoticeBoardVO nBoard = null;
		
		List<FreeBoardVO> flist = new ArrayList<FreeBoardVO>();
		List<ThemeBoardVO> tlist = new ArrayList<ThemeBoardVO>();;
		List<MusicVO> mlist = new ArrayList<MusicVO>();;
		List<NoticeBoardVO> nlist = new ArrayList<NoticeBoardVO>();;
		List<InquiryBoardVO> ilist = new ArrayList<InquiryBoardVO>();;
		
		for(MainVO num : list) {
			if(num.getFree_num()!=0) {
				fBoard = fDao.getBoard(num.getFree_num());
				flist.add(fBoard);
			}
			else if(num.getThe_num()!=0) {
				tBoard = tDao.getBoard(num.getThe_num());
				tlist.add(tBoard);
			}
			else if(num.getMus_num()!=0) {
				music = tDao.getMusic(num.getThe_num());
				mlist.add(music);
			}
			else if(num.getInq_num()!=0) {
				iBoard = iDao.getBoard(num.getInq_num());
				ilist.add(iBoard);
			}
			else if(num.getNot_num()!=0) {
				nBoard = nDao.getBoard(num.getNot_num());
				nlist.add(nBoard);
			}
		}
		
		request.setAttribute("flist", flist);
		request.setAttribute("tlist", tlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("ilist", ilist);
		request.setAttribute("nlist", nlist);
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/main/searchList.jsp";
	}

}
