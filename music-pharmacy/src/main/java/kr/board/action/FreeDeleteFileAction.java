package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class FreeDeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = 
	             new HashMap<String,String>();

		HttpSession session = request.getSession();
		Integer mem_num = 
			(Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 되지 않은 경우
		mapAjax.put("result", "logout");
		}else {//로그인 된 경우
		int free_num = Integer.parseInt(
				request.getParameter("free_num"));
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		FreeBoardVO db_board = dao.getBoard(free_num);
		if(mem_num!=db_board.getMem_num()) {
			mapAjax.put("result", "wrongAccess");
		}else {
			dao.deleteFile(free_num);
			
			//파일 삭제
			FileUtil.removeFile(request, 
					         db_board.getFree_img());
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
