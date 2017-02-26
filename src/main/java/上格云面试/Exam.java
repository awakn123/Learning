package 上格云面试;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by 曹云 on 2017/2/4.
 */
public class Exam {
	public int search(int[] array, int value) {
		int min = 0;
		int max = array.length - 1;
		while (max >= min) {
			int middle = (min + max) / 2;
			if (array[middle] == value) {
				return middle;
			} else if (array[middle] < value) {
				min = middle + 1;
			} else {
				max = middle - 1;
			}
		}
		return -1;
	}


	private void subCombine(List<Integer> a, int n, int m, int b[], int M, List<Integer[]> result) {
		// 注意这里的循环范围
		for (int i = n; i >= m; i--) {
			b[m - 1] = i - 1;
			if (m > 1)
				subCombine(a, i - 1, m - 1, b, M, result);
				// m == 1, 输出一个组合
			else {
				Integer[] p = new Integer[M];
				for (int j = M - 1; j >= 0; j--)
					p[j] = a.get(b[j]);
				result.add(p);
			}
		}
	}

	// 组合算法。
	public List<Integer[]> combine(List<Integer> a, int n, int m) {
		List<Integer[]> result = Lists.newArrayList();
		subCombine(a, n, m, new int[m], m, result);
		return result;
	}


	private void subPermute(int pos, char[] chars, char[] usingChars, int l, int[] usedPos, List<String> result) {
		int n = chars.length;
		if (pos == l) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < l; i++)
				sb.append(usingChars[i]);
			result.add(sb.toString());
			return;
		}
		for (int i = 0; i < n; i++) {
			if (usedPos[i] == 0) {
				usedPos[i]++;
				usingChars[pos] = chars[i];
				subPermute(pos + 1, chars, usingChars, l, usedPos, result);
				usedPos[i]--;
			}
		}
	}

	public List<String> permute(char[] chars, int l) {
		List<String> result = Lists.newArrayList();
		int usedPos[] = new int[chars.length];
		this.subPermute(0, chars, new char[l], l, usedPos, result);
		return result;
	}

	public static void process(Node node) {
		for (int i = 0; i < node.children.size(); i++) {
			Node child = node.children.get(i);
			if (child.value < node.value) {
				process(child);
			}
		}
		System.out.print(node.value + " ");
		for (int i = 0; i < node.children.size(); i++) {
			Node child = node.children.get(i);
			if (child.value >= node.value) {
				process(child);
			}
		}
	}

	public static void newProcess(Node node) {
		Node parentNode = node;
		List<Node> parents = Lists.newArrayList(node);
		Set<Node> hasPrint = Sets.newHashSet();
		while (parentNode != null) {
			boolean smallChange = false;
			Iterator<Node> iter = parentNode.children.iterator();
			while (iter.hasNext()) {
				Node child = iter.next();
				if (child.value < parentNode.value) {
					iter.remove();
					parents.add(parentNode);
					parentNode = child;
					smallChange = true;
					break;
				}
			}
			if (smallChange) {
				continue;
			}
			if (!hasPrint.contains(parentNode)) {
				System.out.print(parentNode.value + " ");
				hasPrint.add(parentNode);
			}
			boolean bigChange = false;
			iter = parentNode.children.iterator();
			while (iter.hasNext()) {
				Node child = iter.next();
				if (child.value >= parentNode.value) {
					iter.remove();
					if (child.children.isEmpty()) {
						System.out.print(child.value + " ");
					} else {
						parents.add(parentNode);
						parentNode = child;
						bigChange = true;
						break;
					}
				}
			}
			if (bigChange) {
				continue;
			}
			if (parents.size() != 0) {
				parentNode = parents.get(parents.size() - 1);
				parents.remove(parents.size() - 1);
			} else {
				parentNode = null;
			}
		}
	}

	/**
	 * 简单起见，不考虑初始数据有错的情况,如node值为10，子节点初始和为20。
	 *
	 * @param node
	 */
	public static void treeCompute(Node node) {
		if (node.value == null || node.children.isEmpty()) {
			return;
		}
		List<Node> nullNodes = Lists.newArrayList();
		double childValue = 0;
		for (Node child : node.children) {
			if (child.value == null) {
				nullNodes.add(child);
			} else {
				childValue += child.value;
			}
		}
		if (nullNodes.isEmpty()) {
			return;
		} else if (nullNodes.size() == 1) {
			nullNodes.get(0).value = node.value - childValue;
		} else {
			double max = node.value - childValue;
			Iterator<Node> nullNodeIter = nullNodes.iterator();
			Map<Node, double[]> rangeMap = Maps.newHashMap();
			while (nullNodeIter.hasNext()) {
				Node n = nullNodeIter.next();
				double[] range = getRangeBySub(n);
				if (range[1] != -1) {
					n.value = range[0];
					max = max - n.value;
					nullNodeIter.remove();
				} else {
					rangeMap.put(n, range);
				}
			}

			Random random = new Random();
			while (nullNodes.size() > 1) {
				Node nullNode = nullNodes.get(0);
				double min = rangeMap.get(nullNode)[0];
				double r = Math.floor(random.nextDouble() * 10) / 10;
				if (r == 0) {
					r = 0.1;
				}
				nullNode.value = r * (max - min) + min;
				max = max - nullNode.value;
				nullNodes.remove(0);
			}
			if (nullNodes.size() == 1) {
				nullNodes.get(0).value = max;
			}
		}
		for (Node child : node.children) {
			treeCompute(child);
		}
	}

	private static double[] getRangeBySub(Node n) {
		double[] range = new double[2];
		if (n.children == null || n.children.isEmpty()) {
			range[1] = -1;//标识为无界
			return range;
		}
		double min = 0;
		double max = 0;
		for (Node c : n.children) {
			if (c.value != null) {
				min += c.value;
			} else {
				double[] cr = getRangeBySub(c);
				min += cr[0];
				if (cr[1] == -1) {
					max = -1;
				}
			}
		}
		if (max != -1) {
			max = min;
		}
		range[0] = min;
		range[1] = max;
		return range;
	}

	public static void main(String[] args) {
		Exam e = new Exam();
		/*Node node = new Node(100d);

		Node child1 = new Node(10d);
		Node child11 = new Node(5.5d);
		Node child12 = new Node(null);
		child1.children.add(child11);
		child1.children.add(child12);

		Node child2 = new Node(20d);
		Node child21 = new Node(9.5d);
		Node child22 = new Node(null);
		Node child23 = new Node(null);
		child2.children.add(child21);
		child2.children.add(child22);
		child2.children.add(child23);

		Node child3 = new Node(null);
		Node child31 = new Node(60d);
		Node child311 = new Node(null);
		Node child312 = new Node(null);
		child31.children.add(child311);
		child31.children.add(child312);
		Node child32 = new Node(null);
		Node child33 = new Node(null);
		child3.children.add(child31);
		child3.children.add(child32);
		child3.children.add(child33);

		Node child4 = new Node(null);
		Node child41 = new Node(null);
		Node child411 = new Node(5.5d);
		Node child412 = new Node(null);
		child41.children.add(child411);
		child41.children.add(child412);
		Node child42 = new Node(null);
		Node child421 = new Node(null);
		Node child422 = new Node(null);
		child42.children.add(child421);
		child42.children.add(child422);
		child4.children.add(child41);
		child4.children.add(child42);

		node.children.add(child1);
		node.children.add(child2);
		node.children.add(child3);
		node.children.add(child4);*/

//		System.out.println("第一题");
//		int[] array = {0,10,20,30,40,50,60,70,80};
//		System.out.println(e.search(array, 10));

//		System.out.println("第二题");
//		char[] chars = {'a', 'b', 'c', 'd'};
////		System.out.println(e.combine(chars, 4, 2));
//		System.out.println(e.permute(chars, 2));

		/*System.out.println("第三题");


		process(node);
		System.out.println();
		newProcess(node);*/
//		System.out.println("第四题");
//		System.out.println(node);
//		treeCompute(node);
//		System.out.println(node);
		System.out.println("逻辑第二题");
		com();
	}

	static void com() {
		Exam e = new Exam();
		List<Integer> nums = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
		List<Integer[]> combines = e.combine(nums, 20, 2);
		List<SetP> setPs = getSetPs(combines);
		Map<Integer, List<SetP>> sumMap = getSumPs(setPs);
		Map<Integer, Integer> productNum = Maps.newHashMap();
		List<List<SetP>> sumPs = Lists.newArrayList(sumMap.values());
		for (List<SetP> sumP : sumPs) {
			for (SetP sp: sumP) {
				if (productNum.get(sp.product) == null) {
					productNum.put(sp.product, 0);
				}
				productNum.put(sp.product, productNum.get(sp.product) + 1);
			}
		}

		Iterator<Map.Entry<Integer, Integer>> iter = productNum.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Integer> entry = iter.next();
			if (entry.getValue() > 1) {
				iter.remove();
			}
		}
		for (SetP sp : setPs) {
			if (productNum.get(sp.product) != null) {
				System.out.println(sp);
			}
		}
	}

	private static Map<Integer, List<SetP>> getSumPs(List<SetP> setPs) {
		Map<Integer, List<SetP>> sumMap = Maps.newHashMap();
		for (SetP sp: setPs) {
			for (Integer[] ele:sp.elements) {
				int sum = ele[0] + ele[1];
				if (sumMap.get(sum) == null) {
					List<SetP> spList = Lists.newArrayList();
					sumMap.put(sum, spList);
				}
				sumMap.get(sum).add(sp);
			}
		}
		List<List<SetP>> sumPs = Lists.newArrayList(sumMap.values());
		Iterator<List<SetP>> iter = sumPs.iterator();
		while(iter.hasNext()) {
			List<SetP> sumps = iter.next();
			if (sumps.size() < 2) {
				iter.remove();
			}
		}
		return sumMap;
	}

	private static List<SetP> getSetPs(List<Integer[]> combines) {
		Map<Integer, SetP> productMap = Maps.newHashMap();
		for (Integer[] num: combines) {
			int p = num[0] * num[1];
			if (productMap.get(p) == null) {
				productMap.put(p, new SetP(p));
			}
			productMap.get(p).elements.add(num);
		}
		List<SetP> setPs = Lists.newArrayList(productMap.values());
		Iterator<SetP> iter = setPs.iterator();
		while(iter.hasNext()) {
			SetP sp = iter.next();
			if (sp.elements.size() < 2) {
				iter.remove();
			}
		}
		return setPs;
	}
}
