package com.kim.biz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

@Controller
@SessionAttributes("member") //@SA의 "이름"지정하여 model에 저장
public class MemberController {
	
	//2022.09.26
	@Autowired
	private MemberService memberService;
	//로그인 화면을 ㄱㄱ
		@RequestMapping(value="/login.do", method=RequestMethod.GET)
		public String index() {
			//데이터 응답 default : forward 방식이 된다.
			return "login.jsp";
		}
		
		//로그인 ㄱㄱ
		@RequestMapping(value="/login.do", method=RequestMethod.POST)
		public String selectOneMember(MemberVO mVO, HttpSession session, Model model){
			//데이터를 넣어서 보낼 떄는 MAV 사용해야 한다.
			//Command 객체를 사용할 때 인자에 New를 해준다.
			System.out.println("여기 지나가니?");
			mVO=memberService.selectOneMember(mVO);
			System.out.println("하이하이");
//			session.setAttribute("userId", mVO); //로그인 시 세션 저장(다른 컨트롤러에서도 사용할 예정)

			if(mVO==null) {
				return "redirect:login.jsp";
			}
			else {
				model.addAttribute("member", mVO); //view를 통한 데이터 제공
				System.out.println("로그인 : "+mVO);
				System.out.println("세션 member 값 : "+session.getAttribute("member"));
				return "main.do";
			}
		}
		//로그아웃
		@RequestMapping("/logout.do")
		public String logout(SessionStatus sessionStatus){
			sessionStatus.setComplete();
			return "redirect:login.do";
		}
		//로그인 > 회원가입
		@RequestMapping(value="signin.do", method=RequestMethod.GET)
		public String signIn() {
			return "signin.jsp";
		}
		
		//회원가입
		@RequestMapping(value="signin.do",method=RequestMethod.POST)
		public String signIn(MemberVO mVO) {
			memberService.insertMember(mVO);
			return "login.jsp";
		}
		//회원탈퇴
		@RequestMapping(value="delete.do")
		public String deleteMember(@ModelAttribute("member")MemberVO mVO, HttpSession session) {
			memberService.deleteMember(mVO);
			session.invalidate();
			return "redirect:login.jsp";
		}
		//마이페이지
		@RequestMapping(value="mypage.do",method=RequestMethod.GET)
		public String mypage(@ModelAttribute("member")MemberVO mVO,HttpSession session) {
			mVO=memberService.selectOneMember(mVO);
			System.out.println("\n마이페이지 : "+mVO);
			System.out.println("세션 member 값 : "+session.getAttribute("member"));
			return "mypage.jsp";
		}
		//회원정보 변경
		@RequestMapping(value="/update.do",method=RequestMethod.POST)
		public String update(@ModelAttribute("member")MemberVO mVO,HttpSession session) {
			memberService.updateMember(mVO);
			System.out.println("\n회원정보 변경 : "+mVO);
			System.out.println("세션 member 값 : "+session.getAttribute("member"));
			return "main.do";
		}
	
	
	//2022.09.22 SpringBoot 스타일로 변경 > output은 String 반환, Model 객체 사용
//	//로그인 화면을 ㄱㄱ
//	@RequestMapping(value="/login.do", method=RequestMethod.GET)
//	public String index() {
//		//데이터 응답 default : forward 방식이 된다.
//		return "login.jsp";
//	}
//	
//	//로그인 ㄱㄱ
//	@RequestMapping(value="/login.do", method=RequestMethod.POST)
//	public String selectOneMember(MemberVO mVO,MemberDAO mDAO, HttpSession session, Model model){
//		//데이터를 넣어서 보낼 떄는 MAV 사용해야 한다.
//		//Command 객체를 사용할 때 인자에 New를 해준다.
//		mVO=mDAO.selectOneMember(mVO);
////		session.setAttribute("userId", mVO); //로그인 시 세션 저장(다른 컨트롤러에서도 사용할 예정)
//
//		if(mVO==null) {
//			return "redirect:login.jsp";
//		}
//		else {
//			model.addAttribute("member", mVO); //view를 통한 데이터 제공
//			System.out.println("로그인 : "+mVO);
//			System.out.println("세션 member 값 : "+session.getAttribute("member"));
//			return "main.do";
//		}
//	}
//	//로그아웃
//	@RequestMapping("/logout.do")
//	public String logout(SessionStatus sessionStatus){
//		sessionStatus.setComplete();
//		return "redirect:login.do";
//	}
//	//회원가입
//	@RequestMapping(value="signin.do",method=RequestMethod.POST)
//	public String signIn(MemberVO mVO, MemberDAO mDAO) {
//		mDAO.insertMember(mVO);
//		return "login.jsp";
//	}
//	//회원탈퇴
//	@RequestMapping(value="delete.do")
//	public String deleteMember(@ModelAttribute("member")MemberVO mVO, MemberDAO mDAO) {
//		mDAO.deleteMember(mVO);
//		return "redirect:login.jsp";
//	}
//	//마이페이지
//	@RequestMapping(value="mypage.do",method=RequestMethod.GET)
//	public String mypage(MemberVO mVO, MemberDAO mDAO, Model model,HttpSession session) {
//		mVO=mDAO.selectOneMember(mVO);
//		System.out.println("\n마이페이지 : "+mVO);
//		System.out.println("세션 member 값 : "+session.getAttribute("member"));
//		return "mypage.jsp";
//	}
//	//회원정보 변경
//	@RequestMapping(value="/update.do",method=RequestMethod.POST)
//	public String update(@ModelAttribute("member")MemberVO mVO, MemberDAO mDAO,HttpSession session) {
//		mDAO.updateMember(mVO);
//		System.out.println("\n회원정보 변경 : "+mVO);
//		System.out.println("세션 member 값 : "+session.getAttribute("member"));
//		return "main.do";
//	}
//	@RequestMapping(value="/main.do")
//	public String handleRequest(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,@ModelAttribute("member")MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, Model model, HttpSession session) {
//		//		public String handleRequest(String searchCondition,String searchContent,@ModelAttribute("userId")MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, Model model) {
//		//자동 매핑이 안됨 > Command 객체로 search에 관한 멤버변수가 없으므로
//		// @requestParam 객체를 통해 사용
//		System.out.println("검색조건 : "+searchCondition);
//		System.out.println("검색어 : "+searchContent);
//
//		if(searchCondition != null) {
//			if(searchCondition.equals("TITLE")) {
//				bVO.setTitle(searchContent);
//			}
//			else if(searchCondition.equals("WRITER")) {
//				bVO.setWriter(searchContent);
//			}
//		}
//
//		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//		//mVO = (MemberVO)session.getAttribute("userId");
////		mVO = mDAO.selectOneMember(mVO);
//		System.out.println("여기 지남? : "+mVO);
//		model.addAttribute("datas",datas);
////		model.addAttribute("member",mVO);
//		return "main.jsp";
//	}
}
