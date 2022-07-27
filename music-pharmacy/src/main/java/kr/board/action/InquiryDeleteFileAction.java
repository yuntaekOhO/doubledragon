package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.InquiryBoardDAO;
import kr.board.vo.InquiryBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class InquiryDeleteFileAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			int inq_num = Integer.parseInt(request.getParameter("inq_num"));
			InquiryBoardDAO dao = InquiryBoardDAO.getInstance();
			InquiryBoardVO db_board = dao.getBoard(inq_num);
			
			if(user_num!=db_board.getMem_num()) {//현재 로그인한 회원번호와 글 작성자의 회원번호와 다른 경우
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteImg(inq_num);
				
				//파일 삭제
				FileUtil.removeFile(request, db_board.getInq_img());
				mapAjax.put("result", "success");
			}
		}
		
		//json 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
