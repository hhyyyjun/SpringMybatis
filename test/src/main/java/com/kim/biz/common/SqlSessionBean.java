package com.kim.biz.common;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
// 이것 때문에 라이브러리를 추가한 것임
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionBean {
	//Mybatis로 DAO 클래스의 CRUD 메서드를 사용
	//Mybatis에서 제공하는 SqlSession 객체를 사용해야 한다.
	//SqlSession은 팩토리 패턴으로 관리되기 때문에 팩토리 객체가 필요하다.
	
	private static SqlSessionFactory sessionFactory = null;
	static {
		try {
			if(sessionFactory==null) {
				//이곳의 작업은 스트림을 사용한다.
				//외부요인으로 인한 에러가 자주 발생하기 때문에 예외처리
				
				//builder를 이용해서 SSF객체를 생성할 예정
				//builder는 Mybatis 설정파일(sql-map-config.xml)을 보고서 메모리에 로딩(적재)해서 SSF객체를 생성한다.
				//설정파일 로딩을 위해 입력 스트림(Reader)을 사용
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml"); //파일을 읽을 땐 리더가 필요
				sessionFactory = new SqlSessionFactoryBuilder().build(reader); //리더로 팩토리 만들고
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		return sessionFactory.openSession(); //팩토리로 세션 만듦
	}
}
