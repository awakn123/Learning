/**
 * Created by 123 on 2020/9/30.
 */
window.solution = {
	longestStrNum: (str) => {
		let map = {}, start = 0, length = 0;
		for (let i=0; i<str.length; i++) {
			let char = str.charAt(i);
			if (map[char] != undefined) {
				start = Math.max(start, map[char] + 1);
			}
			map[char] = i;
			length = Math.max(i - start + 1, length);
		}
		return length;
	}
}

console.log(solution.longestStrNum("abcdefg"));