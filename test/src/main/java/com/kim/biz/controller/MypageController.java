package com.kim.biz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

//@Controller
public class MypageController {
	
//	@RequestMapping(value="mypage.do",method=RequestMethod.GET)
	public ModelAndView mypage(MemberVO mVO, MemberDAO mDAO, ModelAndView mav, HttpSession session) {
		mVO = (MemberVO)session.getAttribute("userId");
		mVO=mDAO.selectOneMember(mVO);
		mav.addObject("member",mVO);
		mav.setViewName("mypage.jsp");
		return mav;
	}
}
