package YingPing;
/** 
* @author 作者 E-mail: zhiqianghu0320@gmail.com
* @version 创建时间：2017年4月29日 下午10:50:44 
* 类说明 :豆瓣的class
*/
public class class_FilmReview {

	//影评编号
	private int articlenum;
	//影评标题
	private String articletitle;
	//影评作者编号(作者主页标识，可能是字符串)
	private String ownernum;
	//影评作者名
	private String ownername;
	//电影/电视剧编号
	private int movienum;
	//电影/电视剧名
	private String moviename;
	//影评作者的电影/电视剧评分(不知道是哪种类型，用string先保存着)--影评只能是整数类型评分，只有电影评分需要根据平均数计算
	private String ratingstr;
	private int ratingint;
	//影评发布时间
	private String time;
	//影评正文
	private String content;
	//影评有用数(暂时没有分隔开)
	private String usefulstr;
	private int usefulint;
	//影评没用数(暂时没有分隔开)
	private String uselessstr;
	private int uselessint;
	//电影/电视剧各种信息
	//电影标题
	private String movietitle;
	
	
	public int getArticlenum() {
		return articlenum;
	}

	public void setArticlenum(int articlenum) {
		this.articlenum = articlenum;
	}

	public String getArticletitle() {
		return articletitle;
	}

	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}

	public String getOwnernum() {
		return ownernum;
	}

	public void setOwnernum(String ownernum) {
		this.ownernum = ownernum;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public int getMovienum() {
		return movienum;
	}

	public void setMovienum(int movienum) {
		this.movienum = movienum;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getRatingstr() {
		return ratingstr;
	}

	public void setRatingstr(String ratingstr) {
		this.ratingstr = ratingstr;
	}

	public int getRatingint() {
		return ratingint;
	}

	public void setRatingint(int ratingint) {
		this.ratingint = ratingint;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsefulstr() {
		return usefulstr;
	}

	public void setUsefulstr(String usefulstr) {
		this.usefulstr = usefulstr;
	}

	public int getUsefulint() {
		return usefulint;
	}

	public void setUsefulint(int usefulint) {
		this.usefulint = usefulint;
	}

	public String getUselessstr() {
		return uselessstr;
	}

	public void setUselessstr(String uselessstr) {
		this.uselessstr = uselessstr;
	}

	public int getUselessint() {
		return uselessint;
	}

	public void setUselessint(int uselessint) {
		this.uselessint = uselessint;
	}

	public String getMovietitle() {
		return movietitle;
	}

	public void setMovietitle(String movietitle) {
		this.movietitle = movietitle;
	}

	public String getMoviedirector() {
		return moviedirector;
	}

	public void setMoviedirector(String moviedirector) {
		this.moviedirector = moviedirector;
	}

	public String getMoviestars() {
		return moviestars;
	}

	public void setMoviestars(String moviestars) {
		this.moviestars = moviestars;
	}

	public String getMovieclass() {
		return movieclass;
	}

	public void setMovieclass(String movieclass) {
		this.movieclass = movieclass;
	}

	public String getMoviearea() {
		return moviearea;
	}

	public void setMoviearea(String moviearea) {
		this.moviearea = moviearea;
	}

	public String getMovieshow() {
		return movieshow;
	}

	public void setMovieshow(String movieshow) {
		this.movieshow = movieshow;
	}


	//电影导演
	public String moviedirector;
	//电影演员
	public String moviestars;
	//电影类型
	public String movieclass;
	//电影地区
	public String moviearea;
	//电影上映
	public String movieshow;
	
	public class_FilmReview(){
		articlenum=-1;
		articletitle=" ";
		ownernum=" ";
		ownername=" ";
		movienum=-1;
		moviename=" ";
		ratingstr=" ";
		ratingint=-1;
		time=" ";
		content=" ";
		usefulstr=" ";
		usefulint=-1;
		uselessstr=" ";
		uselessint=-1;
		movietitle=" ";
		moviedirector=" ";
		moviestars=" ";
		movieclass=" ";
		moviearea=" ";
		movieshow=" ";
	}
	
	public String toString(){
		return "articlenum="+articlenum+"\tarticletitle="+articletitle+"\townernum="+ownernum+"\townername="+ownername+"\tmovienum="+movienum+"\tmoviename="+moviename+"\tratingstr="+
				ratingstr+"\tratingint="+ratingint+"\ttime="+time+"\tcontent="+content+"\tusefulstr="+usefulstr+"\tusefulint="+usefulint+"\tuselessstr="+uselessstr+"\tuselessint="+uselessint+
				"\tmovietitle="+movietitle+"\tmoviedirector="+moviedirector+"\tmoviestars="+moviestars+"\tmovieclass="+movieclass+"\tmoviearea="+moviearea+"\tmovieshow="+movieshow;
	}
	
	public String toWriteString(){
		return articlenum+"\t"+articletitle+"\t"+ownernum+"\t"+ownername+"\t"+movienum+"\t"+moviename+"\t"+
				ratingstr+"\t"+ratingint+"\t"+time+"\t"+content+"\t"+usefulstr+"\t"+usefulint+"\t"+uselessstr+"\t"+uselessint+
				"\t"+movietitle+"\t"+moviedirector+"\t"+moviestars+"\t"+movieclass+"\t"+moviearea+"\t"+movieshow;
	}
	
	public static String toWriteHead(){
		return "articlenum\tarticletitle\townernum\townername\tmovienum\tmoviename\tratingstr\tratingint\ttime\tcontent\tusefulstr\tusefulint\tuselessstr\tuselessint\tmovietitle\tmoviedirector\tmoviestars\tmovieclass\tmoviearea\tmovieshow";
	}
	
	//使用HashSet保存自定义类需要修改equals和hashCode方法
	//不要直接强制类型转化，要先判断类型
	public boolean equals(Object obj){
		if(obj instanceof class_FilmReview){
			class_FilmReview u=(class_FilmReview)obj;
			return (articlenum==u.articlenum);
		}
		return super.equals(obj);
	}
			
			
	public int hashCode() {
	   return (articlenum+"").hashCode();
	}
	
	
}
