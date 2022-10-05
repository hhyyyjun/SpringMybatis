package com.kim.biz.member;

import java.util.List;

public interface MemberService {
	public void insertMember(MemberVO vo);
	public void deleteMember(MemberVO vo);
	public void updateMember(MemberVO vo);
	public MemberVO selectOneMember(MemberVO vo);
	public List<MemberVO> selectAllMember(MemberVO vo);
}
