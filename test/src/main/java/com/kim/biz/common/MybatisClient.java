package com.kim.biz.common;

import java.util.List;

import com.kim.biz.board.BoardVO;
import com.kim.biz.board.impl.BoardDAO3;

public class MybatisClient {
	public static void main(String[] args) {
		BoardDAO3 boardDAO = new BoardDAO3();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("마이바티스");
		vo.setWriter("작티");
		vo.setContent("냠냠냠냠냠냠냠냠냠냠냠냠");
		
		boardDAO.insertBoard(vo);
		
		vo.setSearchCondition("TITLE");
		vo.setSearchContent("이");
		
		List<BoardVO> datas = boardDAO.selectAllBoard(vo);
		for(BoardVO v: datas) {
			System.out.println(v);
		}
	}
}
