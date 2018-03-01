package operation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.category;
import entity.point;

public class readData {
	
	private static List<category> ls;
	private static String filename;
	
	
	public static void read(String file) {
		
		try {
			filename=file;
			ls=new ArrayList<category>();
			FileReader fileReader = new FileReader(filename);
			BufferedReader buf=new BufferedReader(fileReader);
			String linetxt=null;
			int count=0;
			
			for(int i=0;i<getCategoryNum();i++) {
				category c=new category();
				ls.add(c);
			}
			
			while((linetxt=buf.readLine())!=null) {
				
				String[] linedata=linetxt.split(" ");
				int dimension=getDimension();
				//当读取的不是空格时
				if(linedata.length!=1) {
					int[] value=new int[dimension];
					
					for(int i=0;i<getDimension();i++) {
						value[i]=Integer.parseInt(linedata[i+1]);
					}
					
					String name="W"+(count+1);
					
					point p=new point();
					p.setName(linedata[0]);
					p.setValue(value);
					p.setTag(name);
					
					category c=ls.get(count);
					c.add(p);
					c.setName(name);
					ls.set(count, c);
				}else {
					count++;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
	public static List<category> getData(String file){
		read(file);
		return ls;
	}
	
	 public static int getDimension(){
	        int i=0;
	        try{
	            FileReader fileReader=new FileReader(filename);
	            BufferedReader buf=new BufferedReader(fileReader);
	            String linetxt=null;

	            if((linetxt=buf.readLine())!=null) {
	            	linetxt=buf.readLine();
	                String[] linedata = linetxt.split(" ");
	                i=linedata.length;
	            }
	       
	            buf.close();
	            fileReader.close();
	        }catch(Exception e){
	            i=-1;
	        }
	        return i-1;
	    }
	public static int getCategoryNum() {

		int i=1;
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader buf=new BufferedReader(fileReader);
            String linetxt=null;

           while((linetxt=buf.readLine())!=null) {
                String[] linedata = linetxt.split(" ");
                if(linedata.length==1) {
                	i++;
                }
            }
       
            buf.close();
            fileReader.close();
        }catch(Exception e){
            i=-1;
        }
        
        return i;
	}
}
