package utils;

import java.util.Arrays;
import java.util.List;

import entity.point;


public class print {
	
	public static void printPointSet(List<point> lp) {
		
		for(int i=0;i<lp.size();i++) {
			point p=lp.get(i);
			System.out.print(p.getTag_tag()+" "+p.getTag()+" "+p.getName()+" ");
			System.out.println(Arrays.toString(p.getValue()));
		}
	}
	
	
	public static void printVectorSet(List<Float[]> W) {
		
			for(int i=0;i<W.size();i++) {
				Float[] f=W.get(i);
				System.out.println(Arrays.toString(f));
			}
		
		}
}
