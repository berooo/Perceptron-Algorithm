package operation;

import java.util.List;

import entity.category;
import entity.childCategory;
import entity.point;

//测试分类结果是否正确
public class testing {
	
	    //权向量数组列表
		private List<Float[]> W;
		//测试样本点列表
		private List<point> testSample;

		public testing(List<point> testSample,List<Float[]> W) {
			this.W=W;
			this.testSample=testSample;
		}
		
		//分类并计算正确率
		public float classing(String filename) {
					
			int[] types=new int[testSample.size()];
			int count=0;
					
			for(int i=0;i<testSample.size();i++) {
						
				types[i]=classify(testSample.get(i));
				String tag="W"+(types[i]+1);
				String ttag=findFather(tag,filename);
				
				System.out.println(ttag+" "+testSample.get(i).getName());		
				
				if(ttag.equals(testSample.get(i).getTag_tag())) {
					count++;
				}
			}
					
			float ratio=(float)count/testSample.size();
					
			return ratio;
		}
				
		public String findFather(String find,String fileName) {
			
			List<category> lis=readData.getData(fileName);
			for(int i=0;i<lis.size();i++) {
				category c=lis.get(i);
				for(int j=0;j<c.getChildNum();j++) {
					childCategory ch=c.getChildCategory(j);
					if(ch.getName().equals(find)) {
						return ch.getTag();
					}
				}
			}
			return null;
		}
		//对一个点进行分类
		public int classify(point p) {
			
			float[] d=new float[W.size()];
			
			int record=-1;
			
			//获取d值
			for(int i=0;i<W.size();i++) {
				d[i]=cal(p,W.get(i));
			}
			
			record=calMax(d);
			
			return record;	
		}
		
		//计算d值
		public float cal(point p,Float[] w) {
			
			float d=0;
			int[] value=p.getValue();
			
			for(int i=0;i<w.length-1;i++) {
				d+=value[i]*w[i];
			}
			
			d+=w[w.length-1];
			
			return d;
			
		}
		
		//计算最大值的下标
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
