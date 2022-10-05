package com.kim.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

//@Controller
public class SignInController {
	
//	@RequestMapping(value="signin.do",method=RequestMethod.POST)
	public String signIn(MemberVO mVO, MemberDAO mDAO) {
		
		mDAO.insertMember(mVO);
		
		return "login.jsp";
	}
}
