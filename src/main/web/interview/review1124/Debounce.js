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