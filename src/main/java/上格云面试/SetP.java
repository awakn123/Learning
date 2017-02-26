package 上格云面试;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 曹云 on 2017/2/5.
 */
public class SetP {
	List<Integer[]> elements = Lists.newArrayList();
	int product;

	public SetP(){}
	public SetP(int p) {
		product = p;
	}

	@Override
	public String toString() {
		String s = "";
		for (Integer[] ele: elements) {
			s+= Arrays.toString(ele);
		}
		return s;
	}

}
