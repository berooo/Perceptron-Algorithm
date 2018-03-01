package entity;

import java.util.ArrayList;
import java.util.List;

public class category {
	
	private String name;
	private List<point> ls;

	public category() {
		// TODO Auto-generated constructor stub
		ls=new ArrayList<point>();
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void add(point p) {
		ls.add(p);
	}
	
	public List<point> getList(){
		return ls;
	}
	
	public point getPoint(int i) {
		return ls.get(i);
	}

	public void remove(int i) {
		ls.remove(i);
	}
	
	public boolean checkExist(point a) {
		if(ls.contains(a)) {
			return true;
		}
		return false;
	}

}
