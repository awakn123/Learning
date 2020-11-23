let list = [3, 2, 1, 5, 4];
let k = 3;
let add = function(num) {
	list.push(num);
	list.sort((a,b) => {
		return a-b;
	})
	return list[list.length - k];
}