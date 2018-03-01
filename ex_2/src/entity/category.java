package entity;

import java.util.ArrayList;
import java.util.List;

public class category {

	private List<childCategory> lc;
	private String name;
	
	public category() {
		lc=new ArrayList<childCategory>();
	}
	
	public void add(childCategory c) {
		lc.add(c);
	}
	
	public void remove(int i) {
		lc.remove(i);
	}
	
	public childCategory getChildCategory(int i) {
		return lc.get(i);
	}
	
	public void setChildCategory(int i,childCategory c) {
		lc.set(i, c);
	}
	
	public int getChildNum() {
		return lc.size();
	}
	
	public void setName(String name) {
		
		this.name=name;
	}
	
	public String getName() {
		
		return name;
	}
}
