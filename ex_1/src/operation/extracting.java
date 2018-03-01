package operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.category;
import entity.point;

public class extracting {
	
	private static List<category> trainingSample;
	private static List<point> testSample;
	
	
	public static void run(List<category> ls) {
		
		testSample=new ArrayList<point>();
		trainingSample=new ArrayList<category>();
		
		
		for(int i=0;i<ls.size();i++) {
			
			category c=ls.get(i);
			
			//产生随机数,选取一个随机的点加入测试列表
			@SuppressWarnings("unused")
	    	Random r1=new Random();
			@SuppressWarnings("unused")
			int number1=Math.abs(r1.nextInt()%c.getList().size());
			
			point p1=c.getPoint(number1);
			testSample.add(p1);
			c.remove(number1);
			
			Random r2=new Random();
			int number2=Math.abs(r2.nextInt()%c.getList().size());
			point p2=c.getPoint(number2);
			testSample.add(p2);
			c.remove(number2);
			//将剩下的加入训练列表
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
