package operation;

import java.util.List;

import entity.category;
import entity.point;
import utils.print;

public class Main {
	
	public static void main(String[] args){
		
        String fileName="data.txt";
        float c=(float) 0.5;
		
	    List<category> ls=readData.getData(fileName);
		extracting.run(ls);
		
		training tr=new training(extracting.getTrainSample(),c);
		List<Float[]> lf=tr.run();
		
		//测试样本集
		List<point> lp=extracting.getTestSample();
		System.out.println("测试样本集为：");
		print.printPointSet(lp);
		
		System.out.println("分类的结果为：");
		testing test=new testing(lp,lf);
		float ratio=test.classing(fileName);
		System.out.println("分类的正确率为"+ratio*100+"%");
		
		
	}
}
