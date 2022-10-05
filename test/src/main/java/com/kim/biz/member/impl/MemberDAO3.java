package com.kim.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.biz.member.MemberVO;

@Repository("memberDAO")
public class MemberDAO3 {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertMember(MemberVO vo) {
		mybatis.insert("MemberDAO.insertMember",vo);
	}
	public void deleteMember(MemberVO vo) {
		mybatis.delete("MemberDAO.deleteMember",vo);
	}
	public void updateMember(MemberVO vo) {
		mybatis.update("MemberDAO.updateMember",vo);
	}
	public MemberVO selectOneMember(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.selectOneMember",vo);
	}
	public List<MemberVO> selectAllMember(MemberVO vo) {
		return mybatis.selectList("MemberDAO.selectAllMember",vo);
	}
}
