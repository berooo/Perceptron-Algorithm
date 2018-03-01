package operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.category;
import entity.childCategory;
import entity.point;

public class extracting {
	
	private static List<category> trainingSample;
	private static List<point> testSample;
		
	public static void run(List<category> ls) {
		
		testSample=new ArrayList<point>();
		trainingSample=new ArrayList<category>();
		
		for(int i=0;i<ls.size();i++) {
			
			category c=ls.get(i);
			for(int j=0;j<c.getChildNum();j++) {
				
				childCategory ch=c.getChildCategory(j);
				//获取一个随机数
				Random r1=new Random();
				@SuppressWarnings("unused")
				
				int number1=Math.abs(r1.nextInt()%ch.getList().size());
				
				point p1=ch.getPoint(number1);
				testSample.add(p1);
				ch.remove(number1);
				c.setChildCategory(j, ch);
			}
			
			trainingSample.add(c);
		}
	}
	
	public static List<category> getTrainSample(){
		return trainingSample;
	}
	
	public static List<point> getTestSample(){
		return testSample;
	}
}
