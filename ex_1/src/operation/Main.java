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
		
		//ѵ��Ȩ������
		List<Float[]> lf1=t1.run();
		System.out.println("�������1��֪���㷨ѵ�����Ȩ������Ϊ��");
		print.printVectorSet(lf1);
		
		List<Float[]> lf2=t2.run();
		System.out.println("�������3��֪���㷨ѵ�����Ȩ������Ϊ��");
		print.printVectorSet(lf2);
		
		//����������
		List<point> lp=extracting.getTestSample();
		System.out.println("����������Ϊ��");
		print.printPointSet(lp);
		
		//w\!w���ַ�
		System.out.println("w!w���ַ������ ���Ϊ��(w0����IR��)");
		classification_1 s1=new classification_1(lp,lf1);
		float ratio1=s1.classing();
		System.out.println("w\\!w���ַ��������ȷ��Ϊ��"+ratio1*100+"%");
		
		//wi\\wj���ַ�����
		System.out.println("wi,wj���ַ���������Ľ��Ϊ��");
		classification_2 s2=new classification_2(lp,lf2);
		float ratio2=s2.classing();
		System.out.println("wi\\wj���ַ������������ȷ��Ϊ��"+ratio2*100+"%");
		
	}
}
