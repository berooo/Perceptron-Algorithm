package operation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.category;
import entity.childCategory;
import entity.point;

public class readData {
	
	private static List<category> ls;
	private static String filename;
	
	public static void read(String file) {
		
		
		try {
			
			filename=file;
			ls=new ArrayList<category>();
			FileReader fileReader= new FileReader(filename);
			BufferedReader buf=new BufferedReader(fileReader);
			String linetxt=null;
			int count=1;
			//i代表大类,j代表子类
			int i=0,j=0;

			category c=new category();
			c.setName("Z0");
			childCategory cc=new childCategory();
			cc.setName("W1");
			cc.setTag("Z0");
			c.add(cc);
			ls.add(c);

			while((linetxt=buf.readLine())!=null) {
				
				String[] linedata=linetxt.split(" ");
				int dimension=getDimension();
				

				if(linedata.length>1) {
                    int[] value=new int[dimension];
					
					for(int k=0;k<getDimension();k++) {
						value[k]=Integer.parseInt(linedata[k+1]);
					}
					
                    String name="W"+count;
                    String tag_tag="Z"+i;
					
					point p=new point();
					p.setName(linedata[0]);
					p.setValue(value);
					p.setTag(name);
					p.setTag_tag(tag_tag);
					
					category cat=ls.get(i);
				    childCategory chi=cat.getChildCategory(j);
				    chi.add(p);
				}else {
					if(linedata[0].equals(",")) {
						i++;
						category ca=new category();
						
						childCategory ch=new  childCategory();
						ch.setTag("Z"+i);
						ch.setName("W"+(count+1));
						ca.add(ch);
						ca.setName("Z"+i);
						ls.add(ca);
						j=0;
					}else {
						childCategory ch=new  childCategory();
						ch.setName("W"+(count+1));
						ch.setTag("Z"+i);
						category ca=ls.get(i);
						ca.add(ch);
						ca.setName("Z"+i);
						j++;
					}
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
}
