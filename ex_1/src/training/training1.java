package training;

import java.util.ArrayList;
import java.util.List;

import entity.category;
import entity.point;

//��Ӧ��w\!w���ַ��ĸ�֪���㷨
public class training1 {
	
    	//ѵ������
		private List<category> trainingSample;
		//У������
		private float c;
		//Ȩ���������б�
		private List<Float[]> W;
		//���������б�
		private List<point> X;
		private int dimension;
		private category b;
		static int ji=0;
		
		public training1(List<category> trainingSample,float c) {
			this.trainingSample=trainingSample;
			this.c=c;
			W=new ArrayList<Float[]>();
			X=new ArrayList<point>();
			b=new category();
		}
		
		public void initialize() {
			
			
			//��ȡ�������ά��
			dimension=trainingSample.get(0).getPoint(0).getDimension()+1;
			
			//��ʼ������Ȩ����
			for(int i=0;i<trainingSample.size();i++) {
					Float[] f=new Float[dimension];
					for(int j=0;j<f.length;j++) {
						f[j]=(float) 0;
					}
					W.add(f);
			}
			
			//�����������㻯Ϊ����������ʽ
			for(int i=0;i<trainingSample.size();i++) {
				for(int j=0;j<trainingSample.get(i).getList().size();j++) {
					trainingSample.get(i).getPoint(j).intensifier(1);
				}
			}
			
		}
	
		//һ�ε���
		public int iteration(Float[] w) {
			
			int count=0;
			
			for(int i=0;i<X.size();i++) {
				float f=vectorMul(w,X.get(i).getValue());
				if(f<=0) {
					w=justify(w,X.get(i),c,true);
					count++;
					
				}
			}
			
			return count;
			
		}
		
		
		public Float[] calW(category a,category b) {
			
			//��ʼ��Ȩ����
			Float[] w=new Float[dimension];
			for(int i=0;i<w.length;i++) {
				w[i]=(float) 0;
			}
			
			//��X���д���
			for(int i=0;i<a.getList().size();i++) {
				point p=a.getPoint(i);
				int[] value=p.getValue();
				String tag=p.getTag();
				String name=p.getName();
				
				point p1=new point();
				p1.setName(name);
				p1.setTag(tag);
				p1.setValue(value);
				
				X.add(p1);
			}
			for(int i=0;i<b.getList().size();i++) {
				point p=b.getPoint(i);
				int[] value=p.getValue();
				int[] newvalue=new int[value.length];
				for(int j=0;j<value.length;j++) {
					newvalue[j]=-value[j];
				}
				String tag=p.getTag();
				String name=p.getName();
				
				point p1=new point();
				p1.setName(name);
				p1.setTag(tag);
				p1.setValue(newvalue);
				
				X.add(p1);
			}
			
			int sum=0;
			do {
				sum=iteration(w);
			}while(sum!=0);
			
			return w;
			
		}
		//�����޸�Ȩ������ֵ
		public Float[] justify(Float[] W,point p,float c,boolean rp) {
					
			int[] value=p.getValue();
					
			if(rp)//��
			{
			 for(int i=0;i<W.length;i++) {
				W[i]+=c*value[i];
				}
				}else//��
			    {
				 for(int i=0;i<W.length;i++) {
					W[i]-=c*value[i];
				}
			}
			return W;
		}
			
			
		//����������
		public float vectorMul(Float[] W,int[] js) {
			
			float sum=0;
			for(int i=0;i<W.length;i++) {
				sum+=W[i]*js[i];
			}
			return sum;
		}
		
		
		public List<Float[]> run() {
			
			//��ʼ��
			initialize();
			
			//����W
			for(int i=0;i<trainingSample.size();i++) {
				
				category a=trainingSample.get(i);
				category b1=new category();
				b1=calnota(i);
				
				Float[] f=calW(a,b1);
				
				W.set(i, f);
				X.clear();
				b.getList().clear();
			}
			
			release();
			
			return W;
		}
		public void release() {
			for(int i=0;i<trainingSample.size();i++) {
				for(int j=0;j<trainingSample.get(i).getList().size();j++) {
					trainingSample.get(i).getPoint(j).deintensifier();
				}
			}
		}
		
		public category calnota(int i) {
			
			
			for(int j=0;j<trainingSample.size();j++) {
				if(j!=i) {
					for(int k=0;k<trainingSample.get(j).getList().size();k++) {
						point p=trainingSample.get(j).getPoint(k);
						int[] value=p.getValue();
						String tag=p.getTag();
						String name=p.getName();
						
						point p1=new point();
						p1.setName(name);
						p1.setTag(tag);
						p1.setValue(value);
						
						b.add(p1);
					}
				}
			}
			
			return b;
			
		}
}
