package operation;

import java.util.List;

import classification.classification_1;
import classification.classification_2;
import entity.category;
import entity.point;
import training.training1;
import training.training2;
import utils.print;

public class Main {
public static void main(String[] args){
		
		String fileName=args[0];
	    float c=Float.parseFloat(args[1]);
		List<category> ls=readData.getData(fileName);
		extracting.run(ls);
		
		training1 t1=new training1(extracting.getTrainSample(),c);
		training2 t2=new training2(extracting.getTrainSample(),c);
		
		//训练权向量集
		List<Float[]> lf1=t1.run();
		System.out.println("多类情况1感知器算法训练后的权向量集为：");
		print.printVectorSet(lf1);
		
		List<Float[]> lf2=t2.run();
		System.out.println("多类情况3感知器算法训练后的权向量集为：");
		print.printVectorSet(lf2);
		
		//测试样本集
		List<point> lp=extracting.getTestSample();
		System.out.println("测试样本集为：");
		print.printPointSet(lp);
		
		//w\!w两分法
		System.out.println("w!w两分法分类的 结果为：(w0代表IR区)");
		classification_1 s1=new classification_1(lp,lf1);
		float ratio1=s1.classing();
		System.out.println("w\\!w两分法分类的正确率为："+ratio1*100+"%");
		
		//wi\\wj两分法特例
		System.out.println("wi,wj两分法特例分类的结果为：");
		classification_2 s2=new classification_2(lp,lf2);
		float ratio2=s2.classing();
		System.out.println("wi\\wj两分法特例分类的正确率为："+ratio2*100+"%");
		
	}
}
