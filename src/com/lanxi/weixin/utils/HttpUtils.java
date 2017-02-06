package com.lanxi.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.log4j.Logger;

public final class HttpUtils {
	private static Logger log = Logger.getLogger(HttpUtils.class);

	/**
	 * 发�?httpGET请求
	 * @param url 地址
	 * @param charSet 字符�?
	 * @return
	 */
	public static String sendGet(String url,String charSet,String timeOut){
		if (charSet == null || charSet.length() == 0){
			charSet = "UTF-8";
		}
		StringBuffer result = new StringBuffer();
		String timeoutRst="timeOut";
		try {
			URL U = new URL(url);
			URLConnection connection = U.openConnection();
			connection.setConnectTimeout(Integer.parseInt(timeOut));
			connection.connect();
			connection.setReadTimeout(Integer.parseInt(timeOut));
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),charSet));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch(SocketTimeoutException e){
			log.error("ERROR,请求超时 ： "+e);
			return timeoutRst;
		}catch(ConnectTimeoutException e){
			log.error("ERROR,请求超时 ： "+e);
			return timeoutRst;
		}
		catch (MalformedURLException e) {
			log.error("ERROR,"+e.getMessage());
			throw new RuntimeException("发送get请求出错,url:"+url,e);
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR,"+e.getMessage());
			throw new RuntimeException("不支持的字符集charSet:"+charSet,e);
		} catch (IOException e) {
			log.error("ERROR,"+e.getMessage());
			throw new RuntimeException("发送get请求IO出错，url:"+url,e);
		}
		return result.toString();
	}
	
	public static String sendPost(String url,Map<String,String> param,String charSet){
		StringBuffer result = new StringBuffer();
		HttpURLConnection httpConn=null;
		try {
			URL httpurl = new URL(url);
			httpConn = (HttpURLConnection) httpurl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			PrintWriter out = new PrintWriter(httpConn.getOutputStream());
			int i = 0;
			Set<Map.Entry<String, String>> set = param.entrySet();
			for (Map.Entry<String, String> entry:set){//以key=value&key=value形式发送参数
				out.print(entry.getKey());
				out.print("=");
				out.print(entry.getValue());
				if (i!=set.size()-1){
					out.print("&");
				}
				i++;
			}
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charSet));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch (MalformedURLException e) {
			throw new RuntimeException("发送post请求出错,url:"+url,e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的字符集charSet:"+charSet,e);
		} catch (IOException e) {
			throw new RuntimeException("发送post请求IO出错,url:"+url,e);
		}finally{
			httpConn.disconnect();
		}
		log.info("发送post请求成功");
		return result.toString();
	}
	// TODO 可以合并,或者调用减少代码重复率
	public static String sendPost(String url,Map<String,String> param,String charSet,String timeout){
		StringBuffer result = new StringBuffer();
		HttpURLConnection httpConn=null;
		String timeoutRst="timeOut";
		try {
			URL httpurl = new URL(url);
			httpConn = (HttpURLConnection) httpurl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setConnectTimeout(Integer.parseInt(timeout));
			httpConn.setReadTimeout(Integer.parseInt(timeout));
			PrintWriter out = new PrintWriter(httpConn.getOutputStream());
			int i = 0;
			Set<Map.Entry<String, String>> set = param.entrySet();
			for (Map.Entry<String, String> entry:set){
				out.print(entry.getKey());
				out.print("=");
				out.print(entry.getValue());
				if (i!=set.size()-1){
					out.print("&");
				}
				i++;
			}
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charSet));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch(SocketTimeoutException e){
			log.error("ERROR,请求超时 ： "+e);
			return timeoutRst;
		}catch(ConnectTimeoutException e){
			log.error("ERROR,请求超时 ： "+e);
			return timeoutRst;
		} catch (MalformedURLException e) {
			throw new RuntimeException("发送post请求出错,url:"+url,e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的字符集charSet:"+charSet,e);
		} catch (IOException e) {
			throw new RuntimeException("发送post请求IO出错,url:"+url,e);
		}finally{
			httpConn.disconnect();
		}
		return result.toString();
	}
	
	public static String sendPost(String url, String param, String charSet,String charSet1) {
		StringBuffer result = new StringBuffer();
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl
					.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Content-Type", "text/xml;charset="+charSet);
			byte[] ss = param.getBytes(charSet);
			httpConn.setRequestProperty("Accept-Charset", charSet);
			httpConn.setRequestProperty("Content-Length", String.valueOf(ss.length));
			httpConn.setConnectTimeout(120000);
			httpConn.setReadTimeout(120000);
			OutputStream out = httpConn.getOutputStream();
			out.write(ss);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), charSet1));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch (MalformedURLException e) {
			throw new RuntimeException("发送post请求出错,url:" + url, e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的字符集,charSet:" + charSet, e);
		} catch (IOException e) {
			throw new RuntimeException("发送post请求IO出错,url:" + url, e);
		}
		return result.toString();
	}
	
	public static String sendPost(String url, String param, String charSet,String charSet1,String timeout) throws Exception{
		StringBuffer result = new StringBuffer();

		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl
			.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Content-Type", "text/xml;charset="+charSet);
			byte[] ss = param.getBytes(charSet);
			httpConn.setRequestProperty("Accept-Charset", charSet);
			httpConn.setRequestProperty("Content-Length", String.valueOf(ss.length));
			httpConn.setConnectTimeout(Integer.parseInt(timeout));
			httpConn.setReadTimeout(Integer.parseInt(timeout));
			OutputStream out = httpConn.getOutputStream();
			out.write(ss);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), charSet1));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();

		} catch (MalformedURLException e) {
			throw new RuntimeException("发送post请求出错,url:" + url, e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的字符集,charSet:" + charSet, e);
		} catch (IOException e) {
			throw new RuntimeException("发送post请求IO出错,url:" + url, e);
		}
		return result.toString();
	}
	
	public static String sendPostJson(String url, String param, String charSet,String charSet1,String timeout) {
		StringBuffer result = new StringBuffer();
		String timeoutRst="timeOut";
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl
			.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Content-Type", "application/octet-stream;charset="+charSet);
			byte[] ss = param.getBytes(charSet);
			httpConn.setRequestProperty("Accept-Charset", charSet);
			httpConn.setRequestProperty("Content-Length", String.valueOf(ss.length));
			httpConn.setConnectTimeout(Integer.parseInt(timeout));
			httpConn.setReadTimeout(Integer.parseInt(timeout));
			OutputStream out = httpConn.getOutputStream();
			out.write(ss);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), charSet1));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch(SocketTimeoutException e){
			log.error("ERROR,请求超时 ： "+e);
			return timeoutRst;
		}catch(ConnectTimeoutException e){
			log.error("ERROR,请求超时 ： "+e);
			return timeoutRst;
		} catch (MalformedURLException e) {
			throw new RuntimeException("发送post请求出错,url:" + url, e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的字符集,charSet:" + charSet, e);
		} catch (IOException e) {
			throw new RuntimeException("发送post请求IO出错,url:" + url, e);
		}
		return result.toString();
	}
	
}
