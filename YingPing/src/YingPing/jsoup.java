package YingPing;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/** 
* @author 作者 E-mail: zhiqianghu0320@gmail.com
* @version 创建时间：2017年4月10日 下午7:34:09 
* 类说明 
*/
public class jsoup {

	
	//根据url获取网页源代码
	public static String getSource(String url){
		Document doc=new Document("");
		for(int i=0;i<5;i++){
			try{
				doc=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36") .timeout(5000).ignoreContentType(true).get();
				if(doc.toString().length()==0){
					continue;
				}
				return doc.toString();
			}
			catch(Exception e){
				//e.printStackTrace();
				continue;
			}
		}
		return null;
	}
	
	//根据url获取网页源代码
	public static String getSource(String url,int timeout){
		Document doc=new Document("");
		for(int i=0;i<5;i++){
			try{
				doc=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36") .timeout(timeout).ignoreContentType(true).get();
				return doc.toString();
			}
			catch(Exception e){
				//e.printStackTrace();
				continue;
			}
		}
		return null;
	}
	
	//使用代理服务器获取网页源代码
	public static String getSource(String url,int timeout,String ip,int port){
		Document doc=new Document("");
		for(int i=0;i<5;i++){
			try{
				doc=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36").proxy(ip,port).timeout(timeout).ignoreContentType(true).get();
				return doc.toString();
			}
			catch(Exception e){
				//e.printStackTrace();
				continue;
			}
		}
		return null;
	}
	
	//根据url获取网页文本
	public static String getTextByUrl(String url){
		Document doc=new Document("");
		for(int i=0;i<5;i++){
			try{
				doc=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36") .timeout(5000).ignoreContentType(true).get();
				return doc.text();
			}
			catch(Exception e){
				//e.printStackTrace();
				continue;
			}
		}
		return null;
	}
	
	//根据源码获取网页文本
	public static String getTextBySource(String source){
		Document doc=Jsoup.parse(source);
		for(int i=0;i<5;i++){
			try{
				return doc.text();
			}
			catch(Exception e){
				//e.printStackTrace();
				continue;
			}
		}
		return null;
	}
	
	
	//获取网址内所有链接
	public static ArrayList<String> getAllLinksByUrl(String url) {
		ArrayList<String> l=new ArrayList<String> ();
		for(int i=0;i<10;i++){
			try{
				Document doc=Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)") .timeout(5000).ignoreContentType(true).get();
				if(doc==null||doc.toString().length()==0){
					continue;
				}
				Elements eles=doc.getAllElements();
				for(Element ele:eles){
					String link=ele.attr("abs:href");
					if(link!=null&&link.length()>0){
						l.add(link);
					}
				}
				return l;
			}
			catch(Exception e){
				//e.printStackTrace();
				l.clear();
				continue;
			}
		}
		return null;
	}
	
	//获取网址内所有链接
	public static LinkedHashSet<String> getAllLinksByUrlLinkedHashSet(String url) {
		LinkedHashSet<String> l=new LinkedHashSet<String> ();
		for(int i=0;i<10;i++){
			try{
				Document doc=Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36") .timeout(5000).ignoreContentType(true).get();
				Elements eles=doc.getAllElements();
				for(Element ele:eles){
					String link=ele.attr("abs:href");
					if(link!=null&&link.length()>0){
						l.add(link);
					}
				}
				return l;
			}
			catch(Exception e){
				//e.printStackTrace();
				l.clear();
				continue;
			}
		}
		return null;
	}
	
	//获取网址内所有链接
	public static LinkedHashSet<String> getAllLinksBySource(String source) {
		LinkedHashSet<String> l=new LinkedHashSet<String> ();
		for(int i=0;i<10;i++){
			try{
				Document doc=Jsoup.parse(source);
				Elements eles=doc.getAllElements();
				for(Element ele:eles){
					String link=ele.attr("abs:href");
					if(link!=null&&link.length()>0){
						l.add(link);
					}
				}
				return l;
			}
			catch(Exception e){
				//e.printStackTrace();
				l.clear();
				continue;
			}
		}
		return null;
	}
	
	//根据url获取title
	public static String getTitleBySource(String source){
		Document doc=Jsoup.parse(source);
		for(int i=0;i<5;i++){
			try{
				return doc.title();
			}
			catch(Exception e){
				continue;
			}
		}
		return null;
	}

}
