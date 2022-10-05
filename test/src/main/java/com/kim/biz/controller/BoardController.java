package com.kim.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kim.biz.board.BoardService;
import com.kim.biz.board.BoardVO;

@Controller
@SessionAttributes("data")
public class BoardController {

	//2022.09.26
	@Autowired
	private BoardService boardService; //비즈니스 컴포넌트라 부른다. DAO를 직접 이용하지 않는다.

	//String 아웃풋으로 설정
	//게시글 상세보기
	@RequestMapping(value="/board.do",method=RequestMethod.GET)
	public String board(BoardVO bVO, Model model)  { //인자에서 dao가 사라짐
		bVO=boardService.selectOneBoard(bVO);
		model.addAttribute("data",bVO);
		System.out.println("게시글 상세보기1 : "+bVO);
		System.out.println("게시글 상세보기2 : "+bVO.getFileName());
		return "board.jsp";
	}
	//이미지 업로드 2022.09.27 작성
	//게시글 작성
	@RequestMapping("/insertBoard.do")
	public String boardInsert(BoardVO bVO) throws IllegalStateException, IOException {
		//2022.09.27
		MultipartFile uploadFile = bVO.getUploadFile();
		if(!uploadFile.isEmpty()) {//업로드한 파일 존재여부 확인
			String fileName = uploadFile.getOriginalFilename(); //업로드한 파일명
			uploadFile.transferTo(new File("E:\\0607_Lee\\3.wordspaceJSJSP\\test\\src\\main\\webapp\\images\\"+fileName)); //저장할 경로 결정
			bVO.setFileName(fileName);
			System.out.println("게시글 작성1 : 	"+bVO);
			System.out.println("게시글작성파일이름 : "+fileName);
		}
		boardService.insertBoard(bVO);
		System.out.println("게시글 작성 : "+bVO);
		return "main.do";
	}
	//이미지 업로드 2022.09.27 작성
	//게시글 변경
	@RequestMapping(value="/updateBoard.do")
	public String boardUpdate(@ModelAttribute("data")BoardVO bVO) throws IllegalStateException, IOException  {
		//2022.09.27
		MultipartFile uploadFile = bVO.getUploadFile();
		if(!uploadFile.isEmpty()) {//업로드한 파일 존재여부 확인
			String fileName = uploadFile.getOriginalFilename(); //업로드한 파일명
			uploadFile.transferTo(new File("E:\\0607_Lee\\3.wordspaceJSJSP\\test\\src\\main\\webapp\\images\\"+fileName)); //저장할 경로 결정
			bVO.setFileName(fileName);
			System.out.println("게시글 변경 : "+fileName);
		}
		boardService.updateBoard(bVO);
		return "main.do";
	}
	//게시글 삭제
	@RequestMapping("/deleteBoard.do")
	public String boardDelete(@ModelAttribute("data")BoardVO bVO) {
		boardService.deleteBoard(bVO);
		return "main.do";
	}

	//검색관련 map 제공, RequestMapping보다 먼저 호출됨
	@ModelAttribute("scMap")
	public Map<String, String> searchConditionMap(){
		//인자는 없고 Map을 반환할 예정 > 무슨 맵? 검색조건에 들어가는 맵
		Map<String, String> scMap = new HashMap<String,String>();
		//option 태그의 값을 가져옴
		scMap.put("제목", "TITLE"); // ("뷰에 어떻게 보여야 하는지", "실제로 모델에서 쓰는 값") > 컨트롤러가 알기 때문에 관리
		scMap.put("작성자", "WRITER");
		scMap.put("내용", "CONTENT");
		return scMap; // >> model.AddAttribute 된 상태
	}

	//SpringBoot 스타일로 변경 > output이 String 객체반환 > mav 사용x Model 객체 사용
	@RequestMapping(value="/main.do")
	public String handleRequest(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent, BoardVO bVO, Model model, HttpSession session) {
		//		public String handleRequest(String searchCondition,String searchContent,@ModelAttribute("userId")MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, Model model) {
		//자동 매핑이 안됨 > Command 객체로 search에 관한 멤버변수가 없으므로
		// @requestParam 객체를 통해 사용
		//		System.out.println("검색조건 : "+searchCondition);
		//		System.out.println("검색어 : "+searchContent);

		if(searchCondition != null) {
			if(searchCondition.equals("TITLE")) {
				bVO.setTitle(searchContent);
			}
			else if(searchCondition.equals("WRITER")) {
				bVO.setWriter(searchContent);
			}
			else if(searchCondition.equals("CONTENT")) {
				bVO.setContent(searchContent);
			}
		}

		List<BoardVO> datas=boardService.selectAllBoard(bVO);
		System.out.println("세션 memeber 값 : "+session.getAttribute("member"));
		model.addAttribute("datas",datas);
		return "main.jsp";
	}

	//2022.09.22
	//String 아웃풋으로 설정
	//게시글 상세보기
	//	@RequestMapping(value="/board.do",method=RequestMethod.GET)
	//	public String board(BoardVO bVO,BoardDAO bDAO, Model model)  {
	//		bVO=bDAO.selectOneBoard(bVO);
	//		model.addAttribute("data",bVO); //Springboot 방식
	//		return "board.jsp";
	//	}
	//	//게시글 작성
	//	@RequestMapping("/insertBoard.do")
	//	public String boardInsert(BoardVO bVO, BoardDAO bDAO) {
	//		bDAO.insertBoard(bVO);
	//		System.out.println("게시글 작성 : "+bVO);
	//		return "main.do";
	//	}
	//	//게시글 변경
	//	@RequestMapping(value="/updateBoard.do")
	//	public String boardUpdate(@ModelAttribute("data")BoardVO bVO,BoardDAO bDAO)  {
	//		bDAO.updateBoard(bVO);
	//		return "main.do";
	//	}
	//	//게시글 삭제
	//	@RequestMapping("/deleteBoard.do")
	//	public String boardDelete(@ModelAttribute("data")BoardVO bVO, BoardDAO bDAO) {
	//		bDAO.deleteBoard(bVO);
	//		return "main.do";
	//	}
	//
	//	//검색관련 map 제공, RequestMapping보다 먼저 호출됨
	//	@ModelAttribute("scMap")
	//	public Map<String, String> searchConditionMap(){
	//		//인자는 없고 Map을 반환할 예정 > 무슨 맵? 검색조건에 들어가는 맵
	//		Map<String, String> scMap = new HashMap<String,String>();
	//		//option 태그의 값을 가져옴
	//		scMap.put("제목", "TITLE"); // ("뷰에 어떻게 보여야 하는지", "실제로 모델에서 쓰는 값") > 컨트롤러가 알기 때문에 관리
	//		scMap.put("작성자", "WRITER");
	//		return scMap; // >> model.AddAttribute 된 상태
	//	}
	//
	//	//SpringBoot 스타일로 변경 > output이 String 객체반환 > mav 사용x Model 객체 사용
	//	@RequestMapping(value="/main.do")
	//	public String handleRequest(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent, BoardVO bVO,BoardDAO bDAO, Model model, HttpSession session,MemberVO mVO) {
	//		//		public String handleRequest(String searchCondition,String searchContent,@ModelAttribute("userId")MemberVO mVO,MemberDAO mDAO, BoardVO bVO,BoardDAO bDAO, Model model) {
	//		//자동 매핑이 안됨 > Command 객체로 search에 관한 멤버변수가 없으므로
	//		// @requestParam 객체를 통해 사용
	////		System.out.println("검색조건 : "+searchCondition);
	////		System.out.println("검색어 : "+searchContent);
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
	//		System.out.println("\n메인페이지 : "+mVO);
	//		System.out.println("세션 memeber 값 : "+session.getAttribute("member"));
	//		model.addAttribute("datas",datas);
	//		return "main.jsp";
	//	}


	//2022.09.21
	//	@RequestMapping(value="/board.do",method=RequestMethod.GET)
	//	public ModelAndView board(BoardVO bVO,BoardDAO bDAO, ModelAndView mav)  {
	//		bVO=bDAO.selectOneBoard(bVO);
	//
	//		mav.addObject("data", bVO);
	//		mav.setViewName("board.jsp");
	//		return mav;
	//	}

	//2022.09.20
	//	@Override
	//	public ModelAndView handleRequest(HrlwhttpServletRequest request, HttpServletResponse response) throws Exception {
	//		BoardVO bVO=new BoardVO();
	//		bVO.setBid(Integer.parseInt(request.getParameter("bid")));
	//		
	//		BoardDAO bDAO=new BoardDAO();
	//		bVO=bDAO.selectOneBoard(bVO);
	//		
	//		ModelAndView mav=new ModelAndView();
	//		mav.addObject("data", bVO);
	//		mav.setViewName("board");
	//		return mav;
	//	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardVO bVO=new BoardVO();
		bVO.setBid(Integer.parseInt(request.getParameter("bid")));

		BoardDAO bDAO=new BoardDAO();
		bVO=bDAO.selectOneBoard(bVO);

		HttpSession session=request.getSession();
		session.setAttribute("data", bVO);

		return "board";
	}
	 */

}
