/**
 * Created by 123 on 2020/9/30.
 */
window.solution = {
	fibonacci: (num) => {
		if (num == 1)
			return [0];
		let arr = [0, 1];
		for (let i=3; i<=num;i++) {
			let next = arr[arr.length - 1] + arr[arr.length - 2];
			arr.push(next);
		}
		return arr;
	}
};

console.log(solution.fibonacci(10));