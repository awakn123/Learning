package DesignPattern.Clone;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

public class Bean implements Cloneable, Serializable {

	private String name;
	private int num;
	private List l;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List getL() {
		return l;
	}

	public void setL(List l) {
		this.l = l;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public Object deepClone() throws IOException, OptionalDataException,
			ClassNotFoundException {
		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);
		// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());
	}

	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		Bean bean = new Bean();
		bean.setName("name1");
		bean.setNum(1);
		bean.setL(Lists.newArrayList(1,2,3));
		//浅copy也可以复制所有属性，不过属性中的复杂对象都是原来那个，只有最外层的对象不同。
		//深copy可以采用将对象类型的属性另行clone的方式，也可以采用现在的Serialize流的方式。
		//但需要注意的是，Serialize流的模式会将类名、类定义、引用的类等许许多多都写入到流中，开销巨大，不要频繁使用
		//对于频繁使用的，可以手动实现Externalizable接口、序列化成xml或json格式等方式来进行优化。
//		Bean bean2 = (Bean)bean.clone();
		Bean bean2 = (Bean)bean.deepClone();
		System.out.println(bean2.getName());
		System.out.println(bean2.getNum());
		System.out.println(bean2.getL());
		System.out.println(bean2.getName() == bean.getName());
		System.out.println(bean2.getNum() == bean.getNum());
		System.out.println(bean2.getL() == bean.getL());
		System.out.println(bean == bean2);
	}
}
