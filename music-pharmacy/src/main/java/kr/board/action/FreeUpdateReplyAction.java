package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.FreeBoardDAO;
import kr.board.vo.FreeBoardReVO;
import kr.controller.Action;

public class FreeUpdateReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//댓글 번호
		int frely_num = Integer.parseInt(
				request.getParameter("frely_num"));
		
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		FreeBoardReVO db_reply = dao.getReplyBoard(frely_num);
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result","logout");
		}else if(user_num!=null 
				&& user_num == db_reply.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 일치
			FreeBoardReVO reply = new FreeBoardReVO();
			reply.setFreply_num(frely_num);
			reply.setFreply_content(
					request.getParameter("frely_content"));
			
			dao.updateReplyBoard(reply);
			
			mapAjax.put("result", "success");
			
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
