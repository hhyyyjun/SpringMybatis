package com.kim.biz.board;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int bid;
	private String title;
	private String writer;
	private String content;
	private int cnt;
	private String regdate;
	private String searchCondition;
	private String searchContent;
	
	//2022.09.27
	//파일 경로 저장할 변수
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	//스프링에서 제공
	private MultipartFile uploadFile;
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
	//java에서만 사용할 목적으로 VO에 멤버변수 추가
	//그러나 추가를 못하는 상황이 생길수도 있음
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardVO [bid=" + bid + ", title=" + title + ", writer=" + writer + ", content=" + content + ", cnt="
				+ cnt + ", regdate=" + regdate + ", searchCondition=" + searchCondition + ", searchContent="
				+ searchContent + ", fileName=" + fileName + ", uploadFile=" + uploadFile + "]";
	}
	
	
}
