package com.kim.biz.board;

import java.util.List;

public interface BoardService {
	public void insertBoard(BoardVO vo);
	public void updateBoard(BoardVO vo);
	public void deleteBoard(BoardVO vo);
	public BoardVO selectOneBoard(BoardVO vo);
	public List<BoardVO> selectAllBoard(BoardVO vo);
}
