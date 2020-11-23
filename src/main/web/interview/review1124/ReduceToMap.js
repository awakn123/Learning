/**
 这种题，首先要明确map的具体作用：
 mdn中的介绍:
let newArray = arr.map(callback(currentValue[, index[, array]]) {
	// return element for newArray, after executing something
}[, thisArg]);
可以看到， 参数中分别是值，idx，数组，另外还可以传递一个this参数。
不指定的话则是window对象。不知道bind this等会不会起作用。
*/
let arr = [1,2,3];
let obj = {a:1};
arr.map(function (v){
	console.log("this:", this);
});
// Window

arr.map(function (v){
	console.log("this:", this);
}, obj);
// obj;

arr.map(function (v){
	console.log("this:", this);
}.bind(obj));
//obj

let obj2 = {a:2};
arr.map(function (v){
	console.log("this:", this);
}.bind(obj), obj2);
// obj
/**
 * 这样看来是会起作用的，而且绑定之后后面的就不会生效。
 *
 * 然后我们来看一下reduce的作用。
	arr.reduce(callback( accumulator, currentValue[, index[, array]] ) {
	  // return result from executing something for accumulator or currentValue
	}[, initialValue]);
 * 参数为累计值、当前值、序号、数组，另外还可以传入一个初始值。
 * 如果传了初始值，则currentValue从0开始，否则从1开始。
 */

// 所以，首先是方法定义：需要接收两个参数，一个是fn,一个是this.
Array.prototype.map2 = function(fn, callbackThis) {
	// 定义返回值与this逻辑。
	let result = [], cbthis = callbackThis || null;
	this.reduce(function(sum, v, idx, arr){
		result.push(fn.call(callbackThis, v, idx, arr));
	}, 0);// 我们要保证idx从0开始才能达成map的逻辑，所以必须传初始值。
	return result;
}


