package com.alive.networks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiServerMain extends ApiServerContext {
	protected static boolean IS_RUN = true;
	private static Logger logger = LoggerFactory.getLogger(ApiServerMain.class);

	public static void main(String args[]) {
		logger.info("START API Server");
		try{
			init();
			registSignal();
		}catch(Exception e){
			logger.error("Init Error Process KILL: " + e.getMessage(), e);
			System.exit(0);
		}

		while(IS_RUN){
			try{
				threadCollection();
			}catch (Exception e){
				logger.error("", e);
			} finally{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
		logger.info("END API Server");
	}
}
