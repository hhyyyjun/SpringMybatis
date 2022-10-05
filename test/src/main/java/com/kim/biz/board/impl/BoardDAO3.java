package com.kim.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.biz.board.BoardVO;

@Repository("boardDAO")
public class BoardDAO3 {
	
	//마이바티스를 제공하는 곳에서 생성자 주입으로 설정해놨기 때문에 생성자 주입해야 함
	@Autowired
	private SqlSessionTemplate mybatis;
	
//	public BoardDAO3() {
//		//생성자 주입
//		mybatis = SqlSessionBean.getSqlSessionInstance();
//	}
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.insertBoard",vo);
//		mybatis.commit();
	}
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard",vo);
//		mybatis.commit();
	}
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard",vo);
//		mybatis.commit();
	}
	public BoardVO selectOneBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.selectOneBoard",vo);
	}
	public List<BoardVO> selectAllBoard(BoardVO vo) {
//		if(vo.getTitle() != null) {
//			Object[] args= {vo.getTitle()};
//			return mybatis.selectList(sql_selectAll_T,args,vo);
//		}
//		else if(vo.getWriter() != null) {
//			Object[] args= {vo.getWriter()};
//			return mybatis.selectList(sql_selectAll_W,args,vo);
//		}
		return mybatis.selectList("BoardDAO.selectAllBoard",vo);
	}
}
