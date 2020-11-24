// 处理字符串
let str1 = '2123456789';
let str2 = '2123456789.12';

console.log(parseInt(str1).toLocaleString()); // 2,123,456,789
console.log(parseFloat(str2).toLocaleString()); // 2,123,456,789.12
console.log(new Number(str1).toLocaleString()); // 2,123,456,789.12
console.log(new Number(str2).toLocaleString()); // 2,123,456,789.12

// 利用正向预查 匹配 开头一个数字\d 后面匹配这个数字后面必须是三个数字为一组为结尾或小数为结尾
function thousandth(str) {
	let reg = /\d(?=(?:\d{3})+(?:\.\d+|$))/g;
	return str.replace(reg, '$1,');
}
console.log(thousandth(str1)); // 2,123,456,789
console.log(thousandth(str2)); // 2,123,456,789.12

//(x) 匹配 x 并记住它，后续可通过 $1,$2,... 或者 \1,\2,... 来使用
//(?:x) 匹配 x, 这里不会被记住(非捕获元）,消除括号的影响，使其不会影响$1的替换。
//x(?=y) 匹配 x 当且仅当 x 后面跟着 y 时。?=同样为非捕获元。同样的非捕获元还有?!，与?=意义相反。
//+ 贪婪匹配，匹配一个或多个
//$ 匹配输入结束。

