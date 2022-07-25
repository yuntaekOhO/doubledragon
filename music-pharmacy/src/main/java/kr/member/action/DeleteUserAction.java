package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		String user_id = (String)session.getAttribute("user_id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_member = dao.checkMember(id);
		boolean check = false;
		
		if(db_member!=null && id.equals(user_id) && email.equals(db_member.getEmail())){
			check = db_member.isCheckedPassword(passwd);
		}
		
		if(check) {
			dao.deleteMember(user_num);
			FileUtil.removeFile(request, db_member.getPhoto());
			
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/member/deleteUser.jsp";
	}

}