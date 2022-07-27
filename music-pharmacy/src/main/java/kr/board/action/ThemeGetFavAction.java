package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.board.dao.ThemeBoardDAO;
import kr.board.vo.BoardFavVO;


public class ThemeGetFavAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int the_num = Integer.parseInt(
				  request.getParameter("the_num"));
		
		Map<String,Object> mapAjax = 
				          new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		ThemeBoardDAO dao = ThemeBoardDAO.getInstance();
		if(user_num==null) {//로그인 되지 않은 경우
			mapAjax.put("status", "noFav");
			mapAjax.put("count", 
					dao.selectFavCount(the_num));
		}else {//로그인 된 경우
			//로그인된 아이디 셋팅
			BoardFavVO boardFav = 
					dao.selectFav(the_num, user_num);
			
			if(boardFav!=null) {//좋아요 클릭
				mapAjax.put("status", "yesFav");
				mapAjax.put("count", 
						dao.selectFavCount(the_num));
			}else {//좋아요 미클릭
				mapAjax.put("status", "noFav");
				mapAjax.put("count", 
						dao.selectFavCount(the_num));
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
