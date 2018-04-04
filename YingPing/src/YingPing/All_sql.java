package YingPing;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/** 
* @author 作者 E-mail: zhiqianghu0320@gmail.com
* @version 创建时间：2017年5月21日 下午10:40:25 
* 类说明 
*/
public class All_sql {
	
	//随机（用于休眠）
	public static Random ra;
	
	//数据库
	public static Connection con;
	//sql
	public static String sql;
	
	//全局变量
	//年份
	public static String year;
	//排序方式
	public static int sort;
	
	//豆瓣影评表名称
	public static String ArticleTableName="pingjia_";
	//豆瓣电影表名称
	public static String MovieTableName="movie_";
	

	//电影标签页（年份）按照sort排序得到的页数（标注次数排序除外）
	public static int pageNumInYearAndSort;
	//电影标签页爬取到的简单电影信息数据
	public static LinkedHashSet<class_MovieInfo> movieInfoFromPage;
	public static class_MovieInfo[] movieInfoFromPageSz;
	
	//爬到的影评num数据
	public static LinkedHashSet<Integer> ypnums;
	public static Integer[] ypnumsSz;
	
	//爬到的影评数据
	public static ArrayList<class_FilmReview> result; 
	
	//网址开头
	public static final String url_head="https://movie.douban.com/tag/";
	//网址结尾(用来表示排序方式)
	public static String url_end="&type=O";
	
	// File存在且是文件夹则返回true,否则创建新的文件夹并返回true,只有File存在但不是文件夹时才返回false
    public static boolean dirExists(String dirpath) {
    	File file=new File(dirpath);
     	if(file.exists()){
     		if(file.isDirectory()){
     			return true;
            }else{
                return false;
            }
        }else{
        	/*file.mkdirs可以创建多层文件夹,file.mkdir只能创建一层文件夹
        	例如：当前在D://CSDN文件夹下为空,当File file=new File("D://CSDN/FileOperation/Catalog/")时,
        	file.mkdirs()会依次创建FileOperation及其子目录Catalog,file.mkdir()运行不出错但是未成功创建多层目录
        	当File file=new File("D://CSDN/FileOperation/")时,二者效果相同
        	*/
            file.mkdirs();
            return true;
        }
    }
	
	//初始化
	public static boolean init(){
	//	year="2015";
	//	sort=1;
		pageNumInYearAndSort=-1;
		ArticleTableName+=year+"_"+sort;
		MovieTableName+="_"+sort;
		movieInfoFromPage=new LinkedHashSet<class_MovieInfo>();
		ypnums=new LinkedHashSet<Integer>();
		result=new ArrayList<class_FilmReview> ();
		ra=new Random();
		
		//连接数据库
		//初始化连接数据库
		con=MySql.init("douban_yingping");
		//数据库获取失败，直接退出
		if(con==null){
			System.out.println("数据库获取失败!");
			return false;
		}
		//初始化影评表
		sql="create table if not exists "+ArticleTableName+"(articlenum int,articletitle VARCHAR(750),ownernum VARCHAR(750),ownername VARCHAR(750),movienum int,moviename VARCHAR(750),ratingint int,time VARCHAR(750),content LONGTEXT,usefulint int,uselessint int,movietitle VARCHAR(750),moviedirector VARCHAR(750),moviestars VARCHAR(750),movieclass VARCHAR(750),moviearea VARCHAR(750),movieshow VARCHAR(750),"
				+"INDEX(articlenum),INDEX(articletitle),INDEX(ownernum),INDEX(ownername),INDEX(movienum),INDEX(moviename),INDEX(ratingint),INDEX(time),INDEX(usefulint),INDEX(uselessint),INDEX(movietitle),INDEX(moviedirector),INDEX(moviestars),INDEX(movieclass),INDEX(moviearea),INDEX(movieshow))";
		try{
			MySql.exec(con,sql);
			System.out.println("初始化影评表成功!");
		}catch(Exception e){
			System.out.println(sql);
			System.out.println("初始化影评表失败!!");
			e.printStackTrace();
			return false;
		}
		//初始化电影表
		sql="create table if not exists "+ MovieTableName+"(year int,num int,moviename VARCHAR(750),stars VARCHAR(750),shows VARCHAR(750),INDEX(year),INDEX(num),INDEX(moviename),INDEX(stars),INDEX(shows))";
		try{
			MySql.exec(con,sql);
			System.out.println("初始化电影表成功!");
		}catch(Exception e){
			System.out.println(sql);
			System.out.println("初始化电影表失败!!");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//休眠若干毫秒
	public static void sleep(){
		try {
			Thread.sleep(ra.nextInt(50)*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//执行
	public static void start(){
		//根据排序方式修改网址末尾参数
		changeEnd();
		//根据年份获取按照综合排序的页数
		pageNumInYearAndSort=getPageNumByYear();
		
		//页数不对，退出
		if(pageNumInYearAndSort<=0){
			return ;
		}
		
		//自己设定的，航标调试，需要删掉
		//pageNumInYearAndSort=1;
		
		
		//爬取所有电影信息存到movieInfoFromPage里面
		for(int pageNum=0;pageNum<pageNumInYearAndSort;pageNum++){
			//输出用于显示当前是电影第几页
			System.out.println("电影第"+pageNum+"/"+pageNumInYearAndSort+"页");
			ArrayList<class_MovieInfo> bufmovieinfo=getMoviesByPageNum(year,pageNum);
			if(bufmovieinfo!=null&&bufmovieinfo.size()>0){
				for(int i=0;i<bufmovieinfo.size();i++){
					//System.out.println(i+"/"+bufmovieinfo.size()+":"+bufmovieinfo.get(i).num+"\t"+bufmovieinfo.get(i).name);
					movieInfoFromPage.add(bufmovieinfo.get(i));
					
					//写入到数据库
					sql="insert into "+MovieTableName+"(year,num,moviename,stars,shows) values("+year+","+bufmovieinfo.get(i).getNum()+",'"+bufmovieinfo.get(i).getName().replace("'","\\'")+"','"+bufmovieinfo.get(i).getStars().replace("'","\\'")+"','"+bufmovieinfo.get(i).getShow().replace("'","\\'")+"')";
					try{
						MySql.execInsert(con,sql);
					}catch(Exception e){
						System.out.println(sql);
						e.printStackTrace();
						System.out.println("插入到电影表失败!");
					}
				}
			}
			sleep();
		}
//		
//		//遍历所有电影num进一步爬取
//		//存入数组就可以把LinkedHashSet删掉了
//		movieInfoFromPageSz=new class_MovieInfo[movieInfoFromPage.size()];
//		movieInfoFromPage.toArray(movieInfoFromPageSz);
//		movieInfoFromPage.clear();
//		
//		//遍历每一个电影num
//		for(int i=0;i<movieInfoFromPageSz.length;i++){
//			int pageNumInMoviePage=-1;
//			pageNumInMoviePage=getPageNumByMovieNum(movieInfoFromPageSz[i].getNum());
//			//获取了编号为num的影评页数
//			if(pageNumInMoviePage!=-1){
//				System.out.println("第"+i+"/"+movieInfoFromPageSz.length+"部电影:"+movieInfoFromPageSz[i].getNum());
//				//System.out.println(movieInfoFromPageSz[i].num+"\t"+pageNumInMoviePage);
//				//电影编号
//				int MovieNum=movieInfoFromPageSz[i].getNum();
//				
//				//测试用，只遍历第一页(删掉)
//				//pageNumInMoviePage=1;
//				
//				
//				//遍历影评每一页
//				for(int pageNum=0;pageNum<pageNumInMoviePage;pageNum++){
//					System.out.println("第"+i+"/"+movieInfoFromPageSz.length+"部电影:"+movieInfoFromPageSz[i].getNum()+"\t第"+pageNum+"/"+pageNumInMoviePage+"页");	
//					String YPurl="https://movie.douban.com/subject/"+MovieNum+"/reviews?start="+pageNum*20;
//					//System.out.println(YPurl);
//					//根据电影影评页面获取影评num
//					Integer YPNums[]=getYPNum(YPurl);
//					if(YPNums!=null&&YPNums.length>0){
//						for(int j=0;j<YPNums.length;j++){
//							/*System.out.println(YPNums[j]);*/
//							//保存到LinkedHashSet中
//							if(!ypnums.contains(YPNums[j])){
//								ypnums.add(YPNums[j]);
//							}
//						}
//					}
//					sleep();
//				}
//			}
//			sleep();
//		}
//		
//		//到此已经将所有影评的num存到了LinkedHashSet
//		//存入数组就可以把LinkedHashSet删掉了
//		ypnumsSz=new Integer[ypnums.size()];
//		ypnums.toArray(ypnumsSz);
//		ypnums.clear();
//		
//		//遍历爬取到的影评编号，分别获取影评信息
//		for(int i=0;i<ypnumsSz.length;i++){
//			System.out.println("第"+i+"/"+ypnumsSz.length+"个影评:"+ypnumsSz[i]);
//			class_FilmReview cf=FilmReview.getAllDoubanByNum(ypnumsSz[i]);
//			if(cf.getContent()!=null&&cf.getContent().length()>0){
//				//写入到数据库
//				sql="insert into "+ArticleTableName+"(articlenum,articletitle,ownernum,ownername,movienum,moviename,ratingint,time,content,usefulint,uselessint,movietitle,moviedirector,moviestars,movieclass,moviearea,movieshow) values("
//						+ypnumsSz[i]+",'"+cf.getArticletitle().replace("'","\\'")+"','"+cf.getOwnernum().replace("'","\\'")+"','"+cf.getOwnername().replace("'","\\'")+"',"+cf.getMovienum()+",'"+cf.getMoviename().replace("'","\\'")+"',"+cf.getRatingint()+",'"+cf.getTime().replace("'","\\'")+"','"+cf.getContent().replace("'","\\'")+"',"+cf.getUsefulint()+","+cf.getUselessint()+",'"+cf.getMovietitle().replace("'","\\'")+"','"+cf.getMoviedirector().replace("'","\\'")+"','"+cf.getMoviestars().replace("'","\\'")+"','"+cf.getMovieclass().replace("'","\\'")+"','"+cf.getMoviearea().replace("'","\\'")+"','"+cf.getMovieshow().replace("'","\\'")+"')";
//				try{
//					MySql.execInsert(con,sql);
//				}catch(Exception e){
//					System.out.println(sql);
//					e.printStackTrace();
//					System.out.println("插入到影评表失败!");
//				}
//			}
//			sleep();
//		}
		
	}
	
	
	//根据sort的值修改网址排序方式
	public static void changeEnd(){
		switch(sort){
			case 1:
				//综合排序
				url_end="";
				return ;
			case 2:
				//评分排序
				url_end="&type=S";
				return ;
			case 3:
				//日期排序
				url_end="&type=R";
				return ;
			case 4:
				//标注次数排序
				url_end="&type=O";
				return ;
			default:
				//综合排序
				url_end="";
				return ;
		}
	}
	
	//根据年份获取按照综合排序的页数
	public static int getPageNumByYear(){
		String url=url_head+year+url_end;
		System.out.println(url);
		String source=jsoup.getSource(url);
		if(source!=null&&source.length()>0){
			String pat="data-total-page=\"(\\d+?)\"";
			ArrayList<String> re=ZhengZe.getRe(source,pat);
			if(re!=null&&re.size()==1){
				try{
					return Integer.parseInt(re.get(0));
				}catch(Exception e){
					return -1;
				}
			}
		}
		return -1;
	}
	
	//根据年份和页数获取电影信息--日期排序
	//这里的pagenum是从0开始的
	public static ArrayList<class_MovieInfo> getMoviesByPageNum(String year,int pagenum){
		String url=url_head+year+"?start="+pagenum*20+url_end;
		ArrayList<class_MovieInfo> l=new ArrayList<class_MovieInfo> ();
		String source=jsoup.getSource(url);
		if(source!=null&&source.length()>0){
			//先找到所有电影的位置
			Document doc=Jsoup.parse(source);
			Elements infos=doc.select("[class=item]");
			if(infos.size()<21){
				for(int i=0;i<infos.size();i++){
					class_MovieInfo in=new class_MovieInfo();
					String buf=infos.get(i).toString();
					
					//先正则表达式匹配电影num
					String pat_movienum="class=\"nbg\" href=\"https://movie.douban.com/subject/(\\d+?)/\"";
					ArrayList<String> patre=ZhengZe.getRe(buf,pat_movienum);
					if(patre.size()==1){
						try{
							in.setNum(Integer.parseInt(patre.get(0)));
						}catch(Exception e){
							in.setNum(-1);
						}
					}
					
					//先匹配到class=p12再执行匹配电影名称
					Elements pl2s=infos.get(i).select("[class=pl2]");
					if(pl2s.size()==1){
						//jsoup匹配电影名称
						Elements names=pl2s.get(0).select("[href=https://movie.douban.com/subject/"+in.getNum()+"/]");
						if(names.size()==1){
							in.setName(names.get(0).text());
						}
						
						//jsoup匹配主演信息
						Elements stars=pl2s.get(0).getElementsByTag("p");
						if(stars.size()==1){
							in.setStars(stars.get(0).text());
						}
						
						//jsoup匹配上映信息
						Elements shows=pl2s.get(0).select("[class=star clearfix]");
						if(shows.size()==1){
							in.setShow(shows.get(0).text());
						}
					}
					l.add(in);
				}
			}
		}
		return l;
	}
	
	//根据movie的num获取影评页数
	public static int getPageNumByMovieNum(int num){
		String url="https://movie.douban.com/subject/"+num+"/reviews";
		int flag=0;
		while(flag<3){
			String source=jsoup.getSource(url);
			if(source!=null&&source.length()>0){
				String pat="data-total-page=\"(\\d+?)\"";
				ArrayList<String> l=ZhengZe.getRe(source,pat);
				if(l!=null&&l.size()==1){
					return Integer.parseInt(l.get(0));
				}else{
					//没有总页数，说明可能只有一页
					return 1;
				}
			}
			flag++;
		}
		return -1;
	}
	
	//根据电影每一个影评页获取影评编号
	public static Integer[] getYPNum(String url){
		String source=jsoup.getSource(url);
		String pat="href=\"https://movie.douban.com/review/(\\d+?)/\"";
		ArrayList<String> re=ZhengZe.getRe(source, pat);
		if(re.size()<=20){
			LinkedHashSet<Integer> l=new LinkedHashSet<Integer> ();
			for(int i=0;i<re.size();i++){
				if(!l.contains(Integer.parseInt(re.get(i)))){
					//System.out.println(re.get(i));
					l.add(Integer.parseInt(re.get(i)));
				}
			}
			Integer []res=new Integer[l.size()];
			l.toArray(res);
			return res;
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		MySql.user="root";
		MySql.password="9511hyz17";
		year="1988";
		sort=1;
		//初始化--设置一些值和参数
		if(!init()){
			System.out.println("初始化失败!");
			return ;
		}
		//执行
		for(int i = 1988; i <= 2016; ++i) {
			year = String.valueOf(i);
			start();
		}
		
	}

}
