package com.kim.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kim.biz.board.BoardVO;
import com.kim.biz.board.impl.BoardDAO;
import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

//@Controller
//@SessionAttributes("userId")
public class MainController {
	
	//2022.09.22
	//검색관련 map 제공, RequestMapping보다 먼저 호출됨
	@ModelAttribute("scMap")
	public Map<String, String> searchConditionMap(){
		//인자는 없고 Map을 반환할 예정 > 무슨 맵? 검색조건에 들어가는 맵
		Map<String, String> scMap = new HashMap<String,String>();
		//option 태그의 값을 가져옴
		scMap.put("제목", "TITLE"); // ("뷰에 어떻게 보여야 하는지", "실제로 모델에서 쓰는 값") > 컨트롤러가 알기 때문에 관리
		scMap.put("작성자", "WRITER");
		return scMap; // >> model.AddAttribute 된 상태
	}
	
	//SpringBoot 스타일로 변경 > output이 String 객체반환 > mav 사용x Model 객체 사용
//	@RequestMapping(value="/main.do")
	public String handleRequest(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,@ModelAttribute("userId")MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, Model model, HttpSession session) {
//	public String handleRequest(String searchCondition,String searchContent,@ModelAttribute("userId")MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, Model model) {
		//자동 매핑이 안됨 > Command 객체로 search에 관한 멤버변수가 없으므로
		// @requestParam 객체를 통해 사용
		System.out.println("검색조건 : "+searchCondition);
		System.out.println("검색어 : "+searchContent);
		
		if(searchCondition != null) {
			if(searchCondition.equals("TITLE")) {
				bVO.setTitle(searchContent);
			}
			else if(searchCondition.equals("WRITER")) {
				bVO.setWriter(searchContent);
			}
		}
		
		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//		mVO = (MemberVO)session.getAttribute("userId");
		mVO = mDAO.selectOneMember(mVO);
		System.out.println("여기 지남? : "+mVO);
		model.addAttribute("datas",datas);
		model.addAttribute("member",mVO);
		return "main.jsp";
	}
	
	
	//2022.09.21
//	@RequestMapping(value="/main.do")
//	public ModelAndView handleRequest(MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, ModelAndView mav, HttpSession session) {
//		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//		mVO = (MemberVO)session.getAttribute("userId");
//		System.out.println("여기 지남? : "+mVO);
//		mVO = mDAO.selectOneMember(mVO);
//		mav.addObject("member", mVO);
//		mav.addObject("datas", datas);
//		mav.setViewName("main.jsp");
//		return mav;
//	}
	
	//2022.09.20
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		BoardVO bVO=new BoardVO();
//		
//		BoardDAO bDAO=new BoardDAO();
//		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//				
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("datas", datas);
//		mav.setViewName("main");
//		return mav;
//	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardVO bVO=new BoardVO();
		
		BoardDAO bDAO=new BoardDAO();
		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
		
		HttpSession session=request.getSession();
		session.setAttribute("datas", datas);
		
		return "main";
	}
	*/
	
}
