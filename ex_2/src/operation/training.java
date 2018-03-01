package operation;

import java.util.ArrayList;
import java.util.List;

import entity.category;
import entity.childCategory;
import entity.point;


//��Ӧ��wi\wj���ַ������ĸ�֪���㷨
public class training {
	
	//ѵ������
		private List<category> trainingSample;
		//У������
		private float c;
		//Ȩ���������б�
		private List<Float[]> W;
		//���������б�
		private List<point> X;
		static int ji=0;
		
		public training(List<category> trainingSample,float c) {
			this.trainingSample=trainingSample;
			this.c=c;
			W=new ArrayList<Float[]>();
			X=new ArrayList<point>();
		}

	
	public void initialize() {
		
		
		//��ȡ�������ά��
		int dimension=trainingSample.get(0).getChildCategory(0).getPoint(0).getDimension();
		
		//��ʼ������Ȩ����
		for(int i=0;i<trainingSample.size();i++) {
			for(int j=0;j<trainingSample.get(i).getChildNum();j++) {
				
				Float[] f=new Float[dimension+1];
				for(int k=0;k<f.length;k++) {
					f[k]=(float) 0;
				}
				W.add(f);
			}
		}
		
		//�����������㻯Ϊ����������ʽ
		for(int i=0;i<trainingSample.size();i++) {
			for(int j=0;j<trainingSample.get(i).getChildNum();j++) {
				
				for(int k=0;k<trainingSample.get(i).getChildCategory(j).getList().size();k++) {
					trainingSample.get(i).getChildCategory(j).getPoint(k).intensifier(1);
					point p=trainingSample.get(i).getChildCategory(j).getPoint(k);
					X.add(p);
				}		
			}
		}
	}
	//����
			public void iteration_turn() {
				
				int sum=0;
				
				do {
				sum=0;
				for(int i=0;i<X.size();i++) {
					sum+=iteration(X.get(i),W);
				}}while(sum!=0);
			
			}
		
		//һ�ε���
		public int iteration(point p,List<Float[]> W) {
			
			float[] d=new float[W.size()];
			boolean flag=false;
			//�������dֵ
			for(int i=0;i<W.size();i++) {
				
				d[i]=vectorMul(W.get(i),p.getValue());
			}
			
			//�ҳ�p������һ��,����i��¼
			String tag=p.getTag();
			int i=0;
			for(;i<trainingSample.size();i++) {
				if(tag.equals(trainingSample.get(i).getName())) {
					break;
				}
			}
			
			for(int j=0;j<W.size();j++) {
				if(j!=i){
					if(d[i]<=d[j]) {
						Float[] neww1=justify(W.get(j),p,c,false);
						W.set(j, neww1);
						flag=true;
					}
				}
		//		ji++;
		//		System.out.println(ji);
			}
			
			if(flag) {
				Float[] new2=justify(W.get(i),p,c,true);
				W.set(i, new2);
				return 1;
			}
			
			return 0;
		}
		
	   public float vectorMul(Float[] W,int[] js) {
			
			float sum=0;
			for(int i=0;i<W.length;i++) {
				sum+=W[i]*js[i];
			}
			return sum;
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
		
		
		
		//�������뱣����λС��
		public void round() {
			for(int i=0;i<W.size();i++) {
				Float[] f=W.get(i);
				for(int j=0;j<f.length;j++) {
					float fl=f[j];
					float newfl=   (float)(Math.round(fl*100))/100;
					f[j]=newfl;
				}
				W.set(i, f);
			}
		}
		
		
		public List<Float[]> run() {
			
			//��ʼ��
			initialize();
			//����ѵ��
			iteration_turn();
			//�������뱣����λС��
			round();
			
			return W;
		}
}
