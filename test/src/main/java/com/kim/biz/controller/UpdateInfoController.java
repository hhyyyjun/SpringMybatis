package com.kim.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

//@Controller
public class UpdateInfoController {
	
//	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public ModelAndView update(MemberVO mVO, MemberDAO mDAO, ModelAndView mav) {
		mDAO.updateMember(mVO);
		
		
		mav.setViewName("main.do");
		return mav;
		
	}
}
