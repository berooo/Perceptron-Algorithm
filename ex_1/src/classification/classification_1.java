package classification;

import java.util.List;

import entity.point;

//w\!w���ַ�
public class classification_1 {
	
	//Ȩ���������б�
	private List<Float[]> W;
	//�����������б�
	private List<point> testSample;

	public classification_1(List<point> testSample,List<Float[]> W) {
		this.W=W;
		this.testSample=testSample;
	}
	
	//���ಢ������ȷ��
	public float  classing() {
		
		int[] types=new int[testSample.size()];
		int count=0;
		
		for(int i=0;i<testSample.size();i++) {
			
			types[i]=classify(testSample.get(i),i);
			String tag="W"+(types[i]+1);
			System.out.println(tag+" "+testSample.get(i).getName());
			
			if(tag.equals(testSample.get(i).getTag())) {
				count++;
			}
		}
		
		float ratio=(float)count/testSample.size();
		
		return ratio;
	}
	
	//��һ������з���
	public int classify(point p,int k) {
		
		float[] d=new float[W.size()];
		
		int record=-1;
		int count=0;
		
		//��ȡdֵ
		for(int i=0;i<W.size();i++) {
			d[i]=cal(p,W.get(i));
			if(d[i]>0) {
				count++;
				record=i;
			}
		}
		
		if(count==1) {
			return record;
		}

		return -1;		
	}
	
	//����dֵ
	public float cal(point p,Float[] w) {
		
		float d=0;
		int[] value=p.getValue();
		
		for(int i=0;i<w.length-1;i++) {
			d+=value[i]*w[i];
		}
		
		d+=w[w.length-1];
		
		return d;
		
	}
	
}
