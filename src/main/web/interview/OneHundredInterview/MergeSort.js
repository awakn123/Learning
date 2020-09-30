/**
 * Created by 123 on 2020/9/30.
 */
window.solution = {
	mergeSort: (arr) => {
		let tmp = [...arr];
		solution.recurse(arr, tmp, 0, arr.length - 1);
		return arr;
	},
	recurse: (arr, tmp, left, right) => {
		if (left == right)
			return;
		let mid = (left + right)>> 1;
		solution.recurse(arr, tmp, left, mid);
		solution.recurse(arr, tmp, mid + 1, right);
		let l = left, r = mid + 1, idx = left;
		while (l <= mid && r <= right) {
			if (arr[l] < arr[r]) {
				tmp[idx++] = arr[l++];
			} else {
				tmp[idx++] = arr[r++];
			}
		}
		while (l<= mid){
			tmp[idx++] = arr[l++];
		}
		while(r<=right) {
			tmp[idx++] = arr[r++];
		}
		idx = left;
		while(idx<=right) {
			arr[idx] = tmp[idx++];
		}
	}

};

console.log(solution.mergeSort([1,3,5,2,4,6]));
