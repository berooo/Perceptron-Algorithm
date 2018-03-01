package training;

import java.util.ArrayList;
import java.util.List;

import entity.category;
import entity.point;


//对应于wi\wj两分法特例的感知器算法
public class training2 {
	
	//训练样本
	private List<category> trainingSample;
	//校正增量
	private float c;
	//权向量数组列表
	private List<Float[]> W;
	//增广向量列表
	private List<point> X;
	
	public training2(List<category> trainingSample,float c) {
		this.trainingSample=trainingSample;
		this.c=c;
		W=new ArrayList<Float[]>();
		X=new ArrayList<point>();
	}
	
	public void initialize() {
		
		
		//获取样本点的维数
		int dimension=trainingSample.get(0).getPoint(0).getDimension();
		
		//初始化所有权向量
		for(int i=0;i<trainingSample.size();i++) {
				Float[] f=new Float[dimension+1];
				for(int j=0;j<f.length;j++) {
					f[j]=(float) 0;
				}
				W.add(f);
		}
		
		//将所有样本点化为增广向量形式
		for(int i=0;i<trainingSample.size();i++) {
			for(int j=0;j<trainingSample.get(i).getList().size();j++) {
				trainingSample.get(i).getPoint(j).intensifier(1);
				point p=trainingSample.get(i).getPoint(j);
				X.add(p);
			}
		}
		
	}
	//迭代
		public void iteration_turn() {
			
			int sum=0;
			
			do {
			sum=0;
			for(int i=0;i<X.size();i++) {
				sum+=iteration(X.get(i),W);
			}}while(sum!=0);
		
		}
	
	//一次迭代
	public int iteration(point p,List<Float[]> W) {
		
		float[] d=new float[W.size()];
		boolean flag=false;
		//算出所有d值
		for(int i=0;i<W.size();i++) {
			
			d[i]=vectorMul(W.get(i),p.getValue());
		}	
		//找出p属于哪一类,并用i记录
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
	
	
	
	//四舍五入保留两位小数
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
		
		//初始化
		initialize();
		//迭代训练
		iteration_turn();
		//四舍五入保留两位小数
		round();
		
		return W;
	}
	
	
}
