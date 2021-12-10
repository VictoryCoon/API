package com.alive.networks.db.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBSessionFactory {
	private static Logger logger = LoggerFactory.getLogger(DBSessionFactory.class);
	private static SqlSessionFactory factory = null;
	private DBSessionFactory(){}
	
	public static SqlSessionFactory getInstance(){
		return factory;
	}

	public static void init(Properties props, String mybatisConfPath){
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(mybatisConfPath);
		} catch (IOException e) {
			logger.error("", e);
		}

		if(factory == null){
			factory = new SqlSessionFactoryBuilder().build(is, props);
		}
	}

}
