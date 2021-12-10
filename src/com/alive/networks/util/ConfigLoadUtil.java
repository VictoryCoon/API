package com.alive.networks.util;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @usage Key, Value Style Configuration (Auto mapping)
 * @version 2.0
 * @firstCode SHIN EUI CHEOL.
 * @firstUpDate 2015. 01. 26
 * @lastUpdate 2015. 11. 13
 */
public class ConfigLoadUtil {
	 private static Logger logger = LoggerFactory.getLogger(ConfigLoadUtil.class);
	 private String className;
	 private String configPath;

	 public ConfigLoadUtil(String savePkgClassName, String loadConfigPath){
		 this.className = savePkgClassName;
		 this.configPath = loadConfigPath;
	 }

	 public void init(boolean print) throws Exception{
		 FileReader fr = null;
		 try{
			 Properties ppt = new Properties();
			 fr =new FileReader(this.configPath);
			 ppt.load(fr);

			  for(Field field : Class.forName(this.className).getFields()){
				  String key = field.getName();
				  String val = ppt.getProperty(key);
				  String type = field.getType().toString();

				  if(!key.equals("SSL_PASSWORD")) {
					  if(val == null || val.length() == 0){
						 logger.error("ConfigLoad " +  key + " Field is Not Found in ConfigFile");
						 throw new Exception();
					  }
					  else{
						  mappingClassFiled(field,  val, type,print);
					  }
				  }
			  }
		 } catch(Exception e){
			 if(fr !=null){
				 fr.close();
			 }
			 throw e;
		 } finally{
			 if(fr !=null){
				 fr.close();
			 }
		 }
	 }

	 private boolean mappingClassFiled(Field field, String val, String type,boolean print) throws Exception{
		 try{
			 if(type.equalsIgnoreCase("int")){
				 field.setInt(field.getName(), Integer.parseInt(val));
			 }
			 else if(type.equalsIgnoreCase("long")){
				 field.setLong(field.getName(), Long.valueOf(val));
			 }
			 else if(type.equalsIgnoreCase("float")){
				 field.setFloat(field.getName(), Float.valueOf(val));
			 }
			 else if(type.equalsIgnoreCase("double")){
				 field.setDouble(field.getName(), Double.valueOf(val));
			 }
			 else if(type.equalsIgnoreCase("byte")){
				 field.setByte(field.getName(), Byte.valueOf(val));
			 }
			 else if(type.equalsIgnoreCase("char")){
				 field.setChar(field.getName(), val.charAt(0));
			 }
			 else if(type.equalsIgnoreCase("short")){
				 field.setShort(field.getName(), Short.valueOf(val));
			 }
			 else if(type.equalsIgnoreCase("boolean")){
				 if(val.equals("1")){
					 val = "true";
				 }
				 field.setBoolean(field.getName(), Boolean.valueOf(val));
			 }
			 else{
				 field.set(field.getName(), val);
			 }
			 if(print)
			 	logger.info("ConfigLoad -> Key[" + field.getName() + "] Value[" + field.get(field.getName()) + "] Type[" + type +"]");
		 } catch(Exception e){
			 logger.error(field.getName() + " is Load Failed", e);
			 throw e;
		 }
		 return true;
	 }
}
