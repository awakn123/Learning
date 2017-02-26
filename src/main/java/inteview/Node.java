package inteview;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 曹云 on 2017/2/4.
 */
public class Node {
	public Double value;
	public List<Node> children = Lists.newArrayList();
	public Node(){}
	public Node(Double value){
		this.value = value;
	}

	@Override
	public String toString(){
		String s = String.valueOf(value);
		if (!children.isEmpty()) {
			for (Node child: children) {
				s = s + " " + child.toString();
			}
		}
		return s;
	}
}
