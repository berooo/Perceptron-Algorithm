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
		
		//����������
		List<point> lp=extracting.getTestSample();
		System.out.println("����������Ϊ��");
		print.printPointSet(lp);
		
		System.out.println("����Ľ��Ϊ��");
		testing test=new testing(lp,lf);
		float ratio=test.classing(fileName);
		System.out.println("�������ȷ��Ϊ"+ratio*100+"%");
		
		
	}
}
