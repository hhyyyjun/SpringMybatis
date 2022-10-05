package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

/*
package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
*/
//2022.09.22
//dispatcherServlet이 인식하기 때문에 implements Controller가 필요 없음
//@Controller
public class LoginController {
	
	//오버라이딩 필요 X
	//메서드 시그니처 본인 맘대로 변경 가능
//	@RequestMapping("/login.do")
	public void selectOneMember(HttpServletRequest request){
		MemberVO mVO=new MemberVO();
		mVO.setMid(request.getParameter("mid"));
		mVO.setMpw(request.getParameter("mpw"));
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		
		ModelAndView mav=new ModelAndView();
		if(mVO==null) {
			mav.setViewName("redirect:login.jsp");
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			mav.setViewName("redirect:main.do");
		}

	//@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		MemberVO mVO=new MemberVO();
//		mVO.setMid(request.getParameter("mid"));
//		mVO.setMpw(request.getParameter("mpw"));
//		
//		MemberDAO mDAO=new MemberDAO();
//		mVO=mDAO.selectOneMember(mVO);
//		
//		ModelAndView mav=new ModelAndView();
//		if(mVO==null) {
//			mav.setViewName("redirect:login.jsp");
//		}
//		else {
//			HttpSession session=request.getSession();
//			session.setAttribute("member", mVO);
//			
//			mav.setViewName("redirect:main.do");
//		}
//		return mav;
	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		MemberVO mVO=new MemberVO();
		mVO.setMid(mid);
		mVO.setMpw(mpw);
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		
		if(mVO==null) {
			return "login"; // VR가 .jsp를 추가하기때문에 생략해서 반환
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			return "main.do";
		}
	}
	*/

}
