package leetcode.hard.sordandsearch;

public class KthSmallestInTwoSortedArrays {
    int[] arrayM = null; int[] arrayN = null; int k = 0;
    public int getKthSmallest(int[] arrayM, int[] arrayN, int k) {
        this.arrayM = arrayM;
        this.arrayN = arrayN;
        this.k = k;
        if (arrayM.length == 0) {
            return arrayN[k - 1];
        }
        if (arrayN.length == 0) {
            return arrayM[k - 1];
        }
        return getKthSmallestWithMIndex(k/2, k/2);
    }

    public int getKthSmallestWithMIndex(int mi, int step) {
        int ni = this.k - mi - 2;
        if (mi >= this.arrayM.length) {
            return getKthSmallestWithMIndex(this.arrayM.length - 1, step);
        }
        if (ni >= this.arrayN.length) {
            return getKthSmallestWithMIndex(this.k - arrayN.length + 1, step);
        }
        boolean miLessThanNiNext = mi == -1 || (ni == arrayN.length - 1) || arrayM[mi] <= arrayN[ni + 1];
        boolean niLessThanMiNext = ni == -1 || (mi == arrayM.length - 1) || arrayM[mi + 1] >= arrayN[ni];
        if (miLessThanNiNext && niLessThanMiNext) {
            return mi == -1 ? arrayN[ni] :
                    ni == -1 ? arrayM[mi] : Math.max(arrayM[mi], arrayN[ni]);
        }
        step = step/2;
        if (step == 0) {
            step++;
        }
        if (miLessThanNiNext) {
            return getKthSmallestWithMIndex(mi + step, step);
        } else {
            return getKthSmallestWithMIndex(mi - step, step);
        }
    }
}
