package YingPing;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/** 
* @author 作者 E-mail: zhiqianghu0320@gmail.com
* @version 创建时间：2017年5月22日 下午2:35:26 
* 类说明 
*/
public class FilmReview {
	//根据最后面那个编号获取影评网址源代码
	public static String getSourceByNum(int num){
		String url="https://movie.douban.com/review/"+num;
		return jsoup.getSource(url);
	}

	//根据source获取Document（后面都要用）
	public static Document getDocBySource(String source){
		return Jsoup.parse(source);
	}
	
	//根据Document获取影评标题
	public static String getTitleByDocument(Document doc){
		Elements titles=doc.select("[property=v:summary]");
		if(titles.size()==1){
			return titles.get(0).text();
		}
		return null;
	}
		
	//根据Document获取header
	public static Element getHeaderByDocument(Document doc){
		Elements headers=doc.select("[class=main-hd]");
		if(headers.size()==1){
			return headers.get(0);
		}
		return null;
	}
		
	//根据doc和影评编号获取有用或者没用的数量以及推荐的数量
	public static String getInfoByDoc(Document doc,int num,class_FilmReview db){
		//jsoup匹配有用
		Elements useful=doc.select("[class=btn useful_count "+num+" j a_show_login]");
		if(useful.size()==1){
			//System.out.println("有用="+useful.get(0).text());
			db.setUsefulstr(useful.get(0).text().replace("有用 ",""));
			try{
				db.setUsefulint(Integer.parseInt(db.getUsefulstr()));
			}catch(Exception e){
				db.setUsefulint(-1);
			}
		}
			
		//jsoup匹配没用
		Elements useless=doc.select("[class=btn useless_count "+num+" j a_show_login]");
		if(useless.size()==1){
			//System.out.println("没用="+useless.get(0).text());
			db.setUselessstr(useless.get(0).text().replace("没用 ",""));
			try{
				db.setUselessint(Integer.parseInt(db.getUselessstr()));
			}catch(Exception e){
				db.setUselessint(-1);
			}
		}
				
		//jsoup获取电影/电视剧信息
		Elements movie_info=doc.select("[class=sidebar-info-wrapper]");
		if(movie_info.size()==1){
			
			//jsoup匹配title
			Elements titles=movie_info.get(0).select("[class=subject-title]");
			if(titles.size()==1){
				//System.out.println("title="+titles.get(0).text().replace("> ",""));
				db.setMovietitle(titles.get(0).text().replace("> ",""));
			}
				
			//jsoup匹配其它主要信息
			Elements infos=movie_info.get(0).select("[class=info-item]");
			if(infos!=null&&infos.size()>0){
				for(int i=0;i<infos.size();i++){
					////System.out.println(infos.get(i).text());
					String key="";
					String value="";
					Elements keys=infos.get(i).select("[class=info-item-key]");
					if(keys.size()==1){
						key=keys.get(0).text();
					}
					Elements values=infos.get(i).select("[class=info-item-val]");
					if(values.size()==1){
						value=values.get(0).text();
					}
					if(key.length()>0&&value.length()>0){
						switch(key){
							case "导演:":
								//System.out.println("导演="+value);
								db.moviedirector=value;
								break;
							case "主演:":
								//System.out.println("主演="+value);
								db.moviestars=value;
								break;
							case "类型:":
								//System.out.println("类型="+value);
								db.movieclass=value;
								break;
							case "地区:":
								//System.out.println("地区="+value);
								db.moviearea=value;
								break;
							case "上映:":
								//System.out.println("上映="+value);
								db.movieshow=value;
								break;
							default:
								break;
						}
					}
				}
			}
		}
		return "";
	}
	
	//根据header的Element获取各信息(用空格分割，返回字符串)
	public static String getInfoByHeader(Element header,class_FilmReview db){
		String re="";
			
		//正则表达式匹配影评作者编号
		String pat_owner="https://www.douban.com/people/(.+?)/";
		ArrayList<String> owner_num=ZhengZe.getRe(header.toString(),pat_owner);
		////System.out.println(owner_num);
		if(owner_num.size()==1){
			//发现未必是编号，可能是字符串
			//System.out.println("影评作者编号="+owner_num.get(0));
			db.setOwnernum(owner_num.get(0));
		}
		
		//jsoup匹配作者名字
		Elements owner_name=header.select("[property=v:reviewer]");
		if(owner_name.size()==1){
			//System.out.println("影评作者="+owner_name.get(0).text());
			db.setOwnername(owner_name.get(0).text());
		}
			
		//正则表达式匹配电影/电视剧编号
		String pat_movie="https://movie.douban.com/subject/(\\d+?)/";
		ArrayList<String> movie_num=ZhengZe.getRe(header.toString(),pat_movie);
		if(movie_num.size()==1){
			//System.out.println("电影/电视剧编号="+movie_num.get(0));
			db.setMovienum(Integer.parseInt(movie_num.get(0)));
				
			//jsoup匹配电影/电视剧名
			Elements movie_names=header.select("[href=https://movie.douban.com/subject/"+movie_num.get(0)+"/");
			if(movie_names.size()==1){
				//System.out.println("电影/电视剧名="+movie_names.get(0).text());
				db.setMoviename(movie_names.get(0).text());
			}
		}
		
		//jsoup匹配电影/电视剧评分
		Elements rating=header.select("[property=v:rating]");
		if(rating.size()==1){
			//System.out.println("影评作者的电影/电视剧评分="+rating.get(0).text());
			db.setRatingstr(rating.get(0).text());
			try{
				db.setRatingint(Integer.parseInt(db.getRatingstr()));
			}catch(Exception e){
				db.setRatingint(-1);
			}
		}
			
		//jsoup匹配影评发布时间
		Elements time=header.select("[property=v:dtreviewed]");
		if(time.size()==1){
			//System.out.println("影评发布时间="+time.get(0).text());
			db.setTime(time.get(0).text());
		}
		return re;
	}
		
	//根据doc获取影评正文
	public static String getContentByDoc(Document doc){
		//jsoup匹配影评正文
		Elements contents=doc.select("[property=v:description]");
		if(contents.size()==1){
			//System.out.println("影评正文="+contents.get(0).text());
			return contents.get(0).text();
		}
		return "";				
	}
	
	//所有方法合起来
	public static class_FilmReview getAllDoubanByNum(int num){
		class_FilmReview db=new class_FilmReview();
		
		//System.out.println("影评编号="+num);
		db.setArticlenum(num);
		String source=getSourceByNum(num);
		if(source==null||source.length()==0){
			return null;
		}
		////System.out.println(source);
		Document doc=getDocBySource(source);
		if(doc==null){
			return null;
		}
	
		String title=getTitleByDocument(doc);
		if(title!=null&&title.length()>0){
			//System.out.println("title="+title);
			db.setArticletitle(title);
		}
		
		Element header=getHeaderByDocument(doc);
		if(header!=null){
			getInfoByHeader(header,db);
		}
		
		String content=getContentByDoc(doc);
		if(content!=null&&content.length()>0){
			db.setContent(content);
		}
		
		getInfoByDoc(doc,num,db);
		return db;
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		int num=7577093;
		class_FilmReview cd=getAllDoubanByNum(num);
		System.out.println(cd);
		
	}

}
