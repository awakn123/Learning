package Base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 曹云 on 2018/4/1.
 */
public class QuestionOne {
	public static void main(String[] args){
		List<Integer> sumList = Lists.newArrayList(11, 17, 23, 27, 29);
		List<List<Integer>> result = Lists.newArrayList();
		Map<Integer, Integer> productNum = Maps.newHashMap();
		for (int sum: sumList ) {
			for (int i=2; i<sum-i;i++) {
				result.add(Lists.newArrayList(i, sum-i));
				int product = i * (sum - i);
				if (productNum.containsKey(product)) {
					productNum.put(product, productNum.get(product) + 1);
				} else {
					productNum.put(product, 1);
				}
			}
		}

		Iterator<List<Integer>> resultIter = result.iterator();
		while(resultIter.hasNext()) {
			List<Integer> tmp = resultIter.next();
			if (productNum.get(tmp.get(0) * tmp.get(1)) > 1) {
				resultIter.remove();
			}
		}
		System.out.println(result);
		System.out.println(result.size());
		Map<Integer, Integer> sumNum = Maps.newHashMap();
		for (List<Integer> tmp: result) {
			int sum = tmp.get(0) + tmp.get(1);
			if (sumNum.containsKey(sum)) {
				sumNum.put(sum, sumNum.get(sum) + 1);
			} else {
				sumNum.put(sum, 1);
			}
		}
		resultIter = result.iterator();
		while(resultIter.hasNext()) {
			List<Integer> tmp = resultIter.next();
			if (sumNum.get(tmp.get(0) + tmp.get(1)) > 1) {
				resultIter.remove();
			}
		}
		System.out.println(result);
		System.out.println(result.size());
	}
}
