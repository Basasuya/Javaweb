package YingPing;


/** 
* @author 作者 E-mail: zhiqianghu0320@gmail.com
* @version 创建时间：2017年5月1日 下午10:34:10 
* 类说明 :从年份和页数的页面获取的电影简单信息
*/
public class class_MovieInfo {

	//电影id
	private int num;
	//电影名称
	private String name;
	//电影主演
	private String stars;
	//上映信息
	private String show;
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	
	public class_MovieInfo(){
		num=-1;
		name=" ";
		stars=" ";
		show=" ";
	}
	
	//使用HashSet保存自定义类需要修改equals和hashCode方法
	//不要直接强制类型转化，要先判断类型
	public boolean equals(Object obj){
		if(obj instanceof class_MovieInfo){
			class_MovieInfo u=(class_MovieInfo)obj;
			return (num==u.num);
		}
		return super.equals(obj);
	}
		
		
	public int hashCode() {
	   return (num+"").hashCode();
	}
	
	
	public String toString(){
		return num+"\t"+name+"\t"+stars+"\t"+show;
	}
	public String toWriteString(){
		return num+"\t"+name+"\t"+stars+"\t"+show;
	}
	public static String toWriteHead(){
		return "num\tname\tstars\tshow";
	}
	

}
