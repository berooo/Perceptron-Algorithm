package entity;

public class point {
	
	//p所属的类的 名字
	private String tag;
	//p的名字
	private String name;
	private int[] value;
	private int position;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getValue() {
		return value;
	}
	public void setValue(int[] value) {
		this.value = value;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag=tag;
	}
	
	public int getDimension() {
		return value.length;
	}
	
	//增广形式化
	public void intensifier(int a) {
		int[] newvalue=new int[value.length+1];
		for(int i=0;i<value.length;i++) {
			newvalue[i]=value[i];
		}
		newvalue[value.length]=a;
		this.value=newvalue;
	}
	//
	public void deintensifier() {
		int[] newvalue=new int[value.length-1];
		for(int i=0;i<newvalue.length;i++) {
			newvalue[i]=value[i];
		}
		this.value=newvalue;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
