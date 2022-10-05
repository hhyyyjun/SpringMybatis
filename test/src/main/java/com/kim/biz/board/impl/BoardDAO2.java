package com.kim.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kim.biz.board.BoardVO;

//@Repository("boardDAO")
public class BoardDAO2 {

//	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String sql_selectOne="SELECT * FROM BOARD WHERE BID=?";
	final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC";
	final String sql_insert="INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT,IMG) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?,?,?)";
	final String sql_update="UPDATE BOARD SET TITLE=?,CONTENT=?, IMG=? WHERE BID=?";
	final String sql_delete="DELETE BOARD WHERE BID=?";
	final String sql_selectAll_T="SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_selectAll_W="SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID DESC";
	
	public void insertBoard(BoardVO vo) {
		jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getFileName());
	}
	public void updateBoard(BoardVO vo) {
		jdbcTemplate.update(sql_update,vo.getTitle(),vo.getContent(),vo.getFileName(),vo.getBid());
	}
	public void deleteBoard(BoardVO vo) {
		jdbcTemplate.update(sql_delete,vo.getBid());
	}
	public BoardVO selectOneBoard(BoardVO vo) {
		Object[] args= {vo.getBid()};
		return jdbcTemplate.queryForObject(sql_selectOne,args,new BoardRowMapper());
	}
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		if(vo.getTitle() != null) {
			Object[] args= {vo.getTitle()};
			return jdbcTemplate.query(sql_selectAll_T,args,new BoardRowMapper());
		}
		else if(vo.getWriter() != null) {
			Object[] args= {vo.getWriter()};
			return jdbcTemplate.query(sql_selectAll_W,args,new BoardRowMapper());
		}
		return jdbcTemplate.query(sql_selectAll,new BoardRowMapper());
	}
}
class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO data=new BoardVO();
		data.setBid(rs.getInt("BID"));
		data.setContent(rs.getString("CONTENT"));
		data.setTitle(rs.getString("TITLE"));
		data.setWriter(rs.getString("WRITER"));
		data.setCnt(rs.getInt("CNT"));
		data.setRegdate(rs.getString("REGDATE"));
		data.setFileName(rs.getString("IMG"));
		return data;
	}
	
}
