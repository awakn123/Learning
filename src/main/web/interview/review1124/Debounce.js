/**
 函数防抖(debounce)
 概念： 在事件被触发n秒后再执行回调，如果在这n秒内又被触发，则重新计时。
 生活中的实例： 如果有人进电梯（触发事件），那电梯将在10秒钟后出发（执行事件监听器），
 这时如果又有人进电梯了（在10秒内再次触发该事件），我们又得等10秒再出发（重新计时）。

 函数节流(throttle)
 概念： 规定一个单位时间，在这个单位时间内，只能有一次触发事件的回调函数执行，如果在同一个单位时间内某事件被触发多次，只有一次能生效。
 生活中的实例： 我们知道目前的一种说法是当 1 秒内连续播放 24 张以上的图片时，在人眼的视觉中就会形成一个连贯的动画，
 所以在电影的播放（以前是，现在不知道）中基本是以每秒 24 张的速度播放的，
 为什么不 100 张或更多是因为 24 张就可以满足人类视觉需求的时候，100 张就会显得很浪费资源。
 */
const debounce = function(fn, time) {
	let timer;
	return function (...args) {
		clearTimeout(timer);
		let context = this;
		timer = setTimeout(fn.bind(context,...args), time);
	}
}

const throttle = function(fn, time) {
	let startTime, timer;
	return function (...args) {
		if (!startTime || new Date() - startTime > time) {
			startTime = new Date();
			fn.call(this,...args);
			console.log("execute at once.")
		} else if (new Date() - startTime < time) {
			clearTimeout(timer);
			let context = this;
			timer = setTimeout(function(){
				console.log("execute in timeout");
				startTime = new Date();
				fn.call(context,...args);
			}, time);
		}
	}
}

let startTime = new Date();
function print() {
	console.log("print", new Date() - startTime);
}

let p = throttle(print, 10);

while(new Date() - startTime < 100) {
	p();
}