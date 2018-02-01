package probability;

import org.apache.commons.math3.distribution.BinomialDistribution;

public class ProbabilityCompute {
	public static void main(String[] args) {
		double p = 0.035;
		int k = 6;
//		int n = 300
//		double result = computeProbablity(p, k, n);
//		System.out.println(result);
		System.out.println(computeNeedNum(p, k, 0.5));
		System.out.println(computeNeedNum(p, k, 0.9));
		System.out.println(computeNeedNum(p, k, 0.99));
	}

	/**
	 *
	 * @param p 单次出现概率
	 * @param k 需要出现的次数
	 * @param needp 希望抽到的概率（不可能为1）
	 * @return 需要抽的次数
	 */
	public static int computeNeedNum(double p, int k, double needp) {
		if (p >= 1 || needp >= 1 || k <=0)
			return -1;
		int size = 100;
		double gtp = 0d;
		int gtn = 0;
		double ltp = 0d;
		int ltn = 0;
		double sq = 0.005;
		int cn = 0;
		double cp = 0d;
		while (Math.abs(gtp - needp) > sq && Math.abs(ltp - needp) > sq) {
			if (gtp < needp && ltp < needp) {
				if (ltp < gtp) {
					ltp = gtp;
					ltn = gtn;
				}
				gtn += size;
				gtp = computeProbablity(p, k, gtn);
			} else if (gtp > needp && ltp > needp) {
				if (ltp > gtp) {
					gtp = ltp;
					gtn = ltn;
				}
				ltn -= size;
				ltp = computeProbablity(p, k, ltn);
			} else if (gtp> needp && ltp < needp) {
				if (gtn == ltn || gtn - ltn == 1)
					break;
				if (gtn - ltn <= 10) {
					cn = ltn + 1;
				} else {
					cn = (gtn + ltn) / 2;
				}
				cp = computeProbablity(p, k, cn);
				if (cp > needp)  {
					gtp = cp;
					gtn = cn;
				}
				else {
					ltp = cp;
					ltn = cn;
				}
			} else {
				cp = gtp;
				gtp = ltp;
				ltp = cp;
				cn = gtn;
				gtn = ltn;
				ltn = cn;
			}
		}
		if (Math.abs(gtp - needp) < sq)
			return gtn;
		else
			return ltn;
	}

	/**
	 *
	 * @param p 单次出现概率
	 * @param k 需要出现的次数（需要抽到几次）
	 * @param n 执行次数（抽卡次数）
	 * @return
	 */
	private static double computeProbablity(double p, int k, int n) {
		BinomialDistribution binomialDistribution = new BinomialDistribution(n, p);
		double result = 0d;
		for (int i=0;i<k;i++) {
			result += binomialDistribution.probability(i);
		}
		return 1- result;
	}
}
