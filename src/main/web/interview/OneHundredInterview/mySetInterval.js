/**
 * Created by 123 on 2020/9/30.
 * answer: https://github.com/lgwebdream/FE-Interview/issues/7
 */

window.solution = {
	mySetInterval: function(fn, a, b) {
		let execFunc = function(){
			fn();
			let nextTime = a + solution.num * b;
			solution.handle = setTimeout(execFunc, nextTime);
			solution.num++;
		};
		execFunc();
	},
	myClear: function() {
		clearTimeout(solution.handle);
	},
	handle: -1,
	num:0,
};

window.startTime = new Date();
solution.mySetInterval(function(){console.log("test:" + (new Date() - window.startTime)); window.startTime = new Date();}, 1000, 2000);