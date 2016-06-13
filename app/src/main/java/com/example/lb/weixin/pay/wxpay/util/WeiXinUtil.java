/**
* 文件名：WeiXinUtil.java
* 创建日期： 2016-4-6
* 作者：     MaYao
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-4-6
*   修改人：MaYao
*   修改内容：
*/
package com.example.lb.weixin.pay.wxpay.util;


import java.net.InetAddress;
import java.net.UnknownHostException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;



/**
 * 功能描述：
 *		微信开发使用的部分方法
 */
public class WeiXinUtil {

	 /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 获得本机ip
     * @return
     * @throws UnknownHostException
     */
    public static String getIp(){
    	InetAddress addr;
    	String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();//获得本机IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	return ip;
    }
    
    /**
     * 获取时间戳,用户生成商品订单号
     * @return
     */
    public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}


	/**
	 * 生成该笔交易的订单编号
	 * @return
	 */
	private static long orderNum = 0l;
	private static String date ;
	public static synchronized String getOrderNo() {
		String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		if(date==null||!date.equals(str)){
			date = str;
			orderNum  = 0l;
		}
		orderNum ++;
		long orderNo = Long.parseLong((date)) * 100000;
		orderNo += orderNum;;
		return orderNo+"";
	}





}
