package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.NoticeBoardDAO;
import kr.board.vo.NoticeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class NoticeDeleteFileAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지않은 경우
			mapAjax.put("result", "logout");
		}else { //로그인된경우
			int not_num = Integer.parseInt(request.getParameter("not_num"));
			NoticeBoardDAO dao = NoticeBoardDAO.getInstance();
			NoticeBoardVO db_board = dao.getBoard(not_num);
			//그다음조건체크
			if(user_num!=db_board.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteFile(not_num);
				//파일삭제
				FileUtil.removeFile(request, db_board.getNot_img());
				mapAjax.put("result", "success");
			}
		}
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	
}
