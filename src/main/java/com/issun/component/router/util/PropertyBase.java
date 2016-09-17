package com.issun.component.router.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性配置文件的读取，对于配置文件中形如a=b，对于属性a值的读取
 * 
 */
public class PropertyBase {
	
	//------------------------------------------------------- Static Variables
	
	private static final Log LOG = LogFactory.getLog(PropertyBase.class);
	
	private static Map<String,PropertyBase> propertyBases = null;
	
	//------------------------------------------------------- Instance Variables
	
    String cfgFileName = "";

    private boolean loadflag = false;

    private Properties properties;
    
	//------------------------------------------------------- Private Methods
    
    
    /**
     * 私有缺省构造函数
     */
    private PropertyBase() {
        properties = new Properties();
    }
    
    /**
     * 加载属性配置文件
     * 
     * @param filename 属性配置文件所在的文件地址 String
     */
    private void load(String filename) {

        try {
        	//判断文件是否存在
        	File file = new File(filename);
        	if(!file.exists())return;
        	
            //打开环境配置文件
            FileInputStream fi = new FileInputStream(filename);
            //通过properties.load(fi)方面将环境配置文件读入，依次枚举各个环境变量健和值，并配置JVM的环境变量

            properties.load(fi);
            
            fi.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            LOG.error(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex);
        }

    }
    
    /**
     * 将修改和添加新的键值方法统一，通过参数判断
     * @param key 主键 String
     * @param value 对应值
     * @param type 1--写入，0--修改
     * @return
     */
    private boolean write(String key,String value,int type){
    	boolean result = false;
    	
    	File file = new File(this.cfgFileName);
    	if(!file.exists()){
    		try {
				file.createNewFile();
				this.reload();
			} catch (IOException e) {
				LOG.error(e);
			}
    	}
    	
    	if(null != properties){
    		if(1 ==type && !properties.containsKey(key)){//写入及相应的值,如果key存在则不添加 by cgc 2011-11-11
        		properties.put(key, value);
        	}else if(0 == type && properties.containsKey(key)){//对已经存在的key值进行修改
        		properties.put(key, value);
        	}
        	
        	try {
    			OutputStream out = new FileOutputStream(this.cfgFileName);
    			properties.store(out, "");
    			out.close();
    			result = true;
    		} catch (FileNotFoundException e) {
    			LOG.error(e);
    		} catch (IOException e) {
    			LOG.error(e);
    		}
    	}
 		
    	return result;
    }
    
	//------------------------------------------------------- Public Methods
    
    
    /**
     * 单例模式，获取构造对象
     * 
     * @return 属性配置  PropertyBase
     */
    public static PropertyBase getInstance(String fileName) {
    	PropertyBase pb = null;
    	
    	if(null!=propertyBases && propertyBases.containsKey(fileName)){
    		pb = propertyBases.get(fileName);
    	}
        if (pb == null){
        	pb = new PropertyBase();
        	pb.cfgFileName = fileName;
        	pb.init();
        	
        	if(propertyBases==null)propertyBases = new HashMap<String,PropertyBase>();
        	if(propertyBases.containsKey(fileName))propertyBases.remove(fileName);
        	
        	propertyBases.put(fileName, pb);
        }
        return pb;
    }


    /**
     * 初始化配置
     */
    public void init() {
        if (!loadflag) {
            load(this.cfgFileName);
            loadflag = true;
        }
    }


    /**
     * 重新加载属性配置文件
     */
    public void reload() {
        load(this.cfgFileName);
    };


    /**
     * 返回Properties对象
     * @return Properties
     */
    public Properties getProperties(){
    	return this.properties;
    }
    
    /**
     * 根据关键字获取关键字的对应值
     * @param key 关键字 String
     * @return 关键字对应值 String
     */
    public String getValue(String key) {
        return properties.getProperty(key, "");
    }
    
    /**
     * 写入及相应的值,如果key存在则不添加
     * 
     * @param key 主键 String
     * 
     * @param value 对应值
     * 
     * @return 返回写入成功与否 true/false
     */
    public boolean write(String key,String value){
    	return this.write(key, value, 1);
    }
    
    /**
     * 对已经存在的key值进行修改
     * 
     * @param key 主键 String
     * 
     * @param value 对应值
     * 
     * @return 返回写入成功与否 true/false
     */
    public boolean update(String key,String value){
    	return this.write(key, value, 0);
    }
    
    
}