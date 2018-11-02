package com.syaccp.testWeb;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.syaccp.mapper.NewsMapper;

public class testWeb {
	private static SqlSessionFactory sessionFactory; // 连接池

	static {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = builder.build(reader);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteByPrimaryKey() {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			NewsMapper mapper = session.getMapper(NewsMapper.class);
			mapper.deleteByPrimaryKey(709);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			if (session != null) {
				session.close();
			}
		}
		
	}

}
