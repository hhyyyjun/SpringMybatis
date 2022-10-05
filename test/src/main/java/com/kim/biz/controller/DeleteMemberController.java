package com.kim.biz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

//@Controller
public class DeleteMemberController {
	
//	@RequestMapping(value="delete.do")
	public String deleteMember(MemberVO mVO, MemberDAO mDAO, HttpSession session) {
		mVO = (MemberVO)session.getAttribute("userId");
		mDAO.deleteMember(mVO);
		session.invalidate();
		
		return "login.jsp";
	}
	
}
