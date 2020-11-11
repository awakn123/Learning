//实现 sum(1)(2)(3).valueOf()
//支持 sum(1),sum(1)(2,3)(4),sum(1,2,3,4)

function sum() {
	let args = Array.from(arguments);
	let sum = args.reduce((sum,v) => sum+=v);
	function add() {
		let args = Array.from(arguments);
		sum += args.reduce((sum,v) => sum+=v);
		return add;
	}
	add.valueOf = add.toString = function(){
		return sum;
	}
	return add;
}


//实现 taskSum(1000,()=>{console.log(1)}).task(1200,()=>{console.log(2)}).task(1300,()=>{console.log(3)})， 这里等待 1s，打印 1，之后等待 1.2s，打印 2，之后打印 1.3s，打印 3
function taskSum(time, fn){
	function Run() {

	}
	Run.prototype.task = function(time, fn) {
		let curTime = new Date();
		while (new Date() - curTime < time){};
		fn();
		return this;
	}
	return new Run().task(time, fn);
}