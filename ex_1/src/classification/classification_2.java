package classification;

import java.util.List;

import entity.point;

//wi\wj���ַ�����
public class classification_2 {
	
	    //Ȩ���������б�
		private List<Float[]> W;
		//�����������б�
		private List<point> testSample;

		public classification_2(List<point> testSample,List<Float[]> W) {
			this.W=W;
			this.testSample=testSample;
		}
		
		//���ಢ������ȷ��
		public float classing() {
			
			int[] types=new int[testSample.size()];
			int count=0;
			
			for(int i=0;i<testSample.size();i++) {
				
				types[i]=classify(testSample.get(i));
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
		public int classify(point p) {
			
			float[] d=new float[W.size()];
			
			int record=-1;
			
			//��ȡdֵ
			for(int i=0;i<W.size();i++) {
				d[i]=cal(p,W.get(i));
			}
			
			record=calMax(d);
			
			return record;	
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
		
		//�������ֵ���±�
		public int calMax(float[] d) {
			
			int index=-1;
			float max=-Float.MAX_VALUE;
			for(int i=0;i<d.length;i++) {
				if(d[i]>max) {
					max=d[i];
					index=i;
				}
			}
			int count=0;
			for(int i=0;i<d.length;i++) {
				if(d[i]==max) {
					count++;
				}
			}
			
			if(count>1) {
				index=-1;
			}
			return index;
		}

}
