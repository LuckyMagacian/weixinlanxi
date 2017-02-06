package com.lanxi.weixin.utils;

import java.util.Arrays;
import java.util.List;

import com.lanxi.weixin.manager.ParamManager;

public class MobileUtil {
	
	/**
	 * 各大运营商号段归纳如下：
	 * 中国电信：133、153、180、189 、177。
	 * 中国联通：130、131、132、155、156、185、186 、176。
	 * 中国移动：134、135、136、137、138、139、147、150、151、152、157、158、159、182、187、188 、178。
	 * 虚拟运营商：170。
	 * 通过看手机号码前三位，根据上述信息也可以判断手机号码是哪个运营商的。
	 * @param mobile
	 * @return	如果没有找到，默认返回移动
	 */
    public static int getMobileType(String mobile) {
    	String[] cMobile = ParamManager.chinaMobile.split(",");
    	String[] cUnicom = ParamManager.chinaUnicom.split(",");
    	String[] cTelecom = ParamManager.chinaTelecom.split(",");
    	/*List<String> chinaMobile = Arrays.asList(new String[] {ParamManager.chinaMobile}) ;
    	List<String> chinaUnicom = Arrays.asList(new String[] {ParamManager.chinaUnicom}) ;
    	List<String> chinaTelecom = Arrays.asList(new String[] {ParamManager.chinaTelecom});*/
    	List<String> chinaMobile = Arrays.asList(cMobile);
    	List<String> chinaUnicom = Arrays.asList(cUnicom);
    	List<String> chinaTelecom = Arrays.asList(cTelecom);
    	String str = mobile.substring(0,3);
    	boolean bolChinaMobile = (chinaMobile.contains(str)) ;
    	boolean bolChinaUnicom  = (chinaUnicom.contains(str)) ;
        boolean bolChinaTelecom = (chinaTelecom.contains(str)) ;
        if(bolChinaMobile){
            return 1; //移动
        }
        if(bolChinaUnicom){
            return 2;//联通
        }
        if(bolChinaTelecom){
        	return 3; //电信
        }
        return 1;
    } 
	
	public static void main(String[] args) {
		ParamManager.readConfig();
		String mobile="18068876857";
		System.out.println(getMobileType(mobile));
	}

}
