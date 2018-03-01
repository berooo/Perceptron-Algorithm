package training;

import java.util.ArrayList;
import java.util.List;

import entity.category;
import entity.point;

//对应于w\!w两分法的感知器算法
public class training1 {
	
    	//训练样本
		private List<category> trainingSample;
		//校正增量
		private float c;
		//权向量数组列表
		private List<Float[]> W;
		//增广向量列表
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
			
			
			//获取样本点的维数
			dimension=trainingSample.get(0).getPoint(0).getDimension()+1;
			
			//初始化所有权向量
			for(int i=0;i<trainingSample.size();i++) {
					Float[] f=new Float[dimension];
					for(int j=0;j<f.length;j++) {
						f[j]=(float) 0;
					}
					W.add(f);
			}
			
			//将所有样本点化为增广向量形式
			for(int i=0;i<trainingSample.size();i++) {
				for(int j=0;j<trainingSample.get(i).getList().size();j++) {
					trainingSample.get(i).getPoint(j).intensifier(1);
				}
			}
			
		}
	
		//一次迭代
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
			
			//初始化权向量
			Float[] w=new Float[dimension];
			for(int i=0;i<w.length;i++) {
				w[i]=(float) 0;
			}
			
			//对X进行处理
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
		//调整修改权向量的值
		public Float[] justify(Float[] W,point p,float c,boolean rp) {
					
			int[] value=p.getValue();
					
			if(rp)//赏
			{
			 for(int i=0;i<W.length;i++) {
				W[i]+=c*value[i];
				}
				}else//罚
			    {
				 for(int i=0;i<W.length;i++) {
					W[i]-=c*value[i];
				}
			}
			return W;
		}
			
			
		//计算向量积
		public float vectorMul(Float[] W,int[] js) {
			
			float sum=0;
			for(int i=0;i<W.length;i++) {
				sum+=W[i]*js[i];
			}
			return sum;
		}
		
		
		public List<Float[]> run() {
			
			//初始化
			initialize();
			
			//计算W
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
