package com.alive.networks;

import java.util.Properties;

import com.alive.networks.config.CommonConfig;
import com.alive.networks.config.ProcessConfig;
import com.alive.networks.db.session.DBSessionFactory;
//import com.alive.networks.util.AESUtil;
import com.alive.networks.util.ConfigLoadUtil;
import com.alive.networks.worker.network.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiServerContext {
	private static Logger logger = LoggerFactory.getLogger(ApiServerContext.class);
	
	protected static Thread httpParentThread=null;
	
	//public static final String HOME_PATH = System.getProperty("HOME_PATH");
	public static final String HOME_PATH = "/Users/coon/Documents/IntelliJ/API";
	//public static final String HOME_PATH = "/home/ippush151/API";
	public static final String PROCESS_CONFIG = "/config/APIConfig.properties";
	public static final String COMMON_CONFIG = "/config/Config.properties";
	/* 일반 Config 경로와는 구분됐음. 마이바티스 경로는 현재경로이하로 확인함.*/
	public static final String SQL_CONFIG_NAME = "./db-mapper/mybatis.configuration.xml";

	protected static void init() throws Exception{
		logger.info("Default encoding : " + System.getProperty("file.encoding"));
		if(HOME_PATH == null || HOME_PATH.length() == 0){
			logger.error("Property CONFIG_HOME NOT FOUND");
			throw new Exception();
		}
		initConfigFile();
		initDatabase();
	}
	
	private static void initConfigFile() throws Exception {
		String module_path=System.getProperty("user.dir");
		String[] dir=module_path.split("/");
		String curDir=dir[dir.length-1];
		String gatewayRoot=module_path.replace("/"+curDir,"");

		new ConfigLoadUtil(ProcessConfig.class.getName(), HOME_PATH+PROCESS_CONFIG).init(true);
		logger.info("API Server Config Load");

		new ConfigLoadUtil(CommonConfig.class.getName(), HOME_PATH+COMMON_CONFIG).init(false);
		logger.info("Common Config Load");
    }
	 
	private static void initDatabase() throws Exception{
		String dbUserName="", dbPass="";
//		AES256Util aes256 = new AES256Util(Constant.KEY_PASS);

		//dbUserName = CommonConfig.DB_USERNAME_ENCRYPTION ? AESUtil.Decrypt(CommonConfig.DB_USERNAME,AESUtil.KEY_PASS):CommonConfig.DB_USERNAME;
		//dbPass =     CommonConfig.DB_PASSWORD_ENCRYPTION ? AESUtil.Decrypt(CommonConfig.DB_PASSWORD,AESUtil.KEY_PASS):CommonConfig.DB_PASSWORD;
		Properties props = new Properties();
		props.setProperty("driver",   CommonConfig.DB_DRIVER);
		props.setProperty("url",      CommonConfig.DB_URL);
		props.setProperty("username", CommonConfig.DB_USERNAME);
		props.setProperty("password", CommonConfig.DB_PASSWORD);
		
		DBSessionFactory.init(props, SQL_CONFIG_NAME);
		logger.info("Init DB Load");
	}
	
	public static void threadCollection() throws Exception{
		if(httpParentThread == null || httpParentThread.isAlive() == false){
			httpParentThread = new Thread(new HttpServer(ProcessConfig.RECEIVER_LISTEN_PORT, ProcessConfig.RECEIVER_THREAD_COUNT));
			httpParentThread.setName("HttpTestParent");
			httpParentThread.start();
		}	
	}
	
    protected static void registSignal() throws Exception{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				ApiServerMain.IS_RUN = false;
				try{

				}catch(Exception e){
					logger.error("Exit Error", e);
				}
	  	      }
	  	});
    }		
}
