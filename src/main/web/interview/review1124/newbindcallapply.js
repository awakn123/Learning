// new:
// new会声明一个对象，对象的构造函数是传过来的函数，构造函数执行时的this是声明的对象
// 原型链(__proto__)是传过来的函数的prototype，
// 返回值：如果function有返回值，则返回该返回值；如果没有，返回该Object.
// 最后加一些corner case;
function _new(func, ...args) {
	if (!(func instanceof Function)) {
		throw new Error('arguments[0] require a function');
	}
	let obj = {};
	obj.__proto__ = func.prototype;
	// ES6写法
	// let obj = Object.create(func.prototype);

	let res = func.call(obj,...args);
	return (res != null && typeof(result) == 'object') ? res : obj;
}

// call:
// call会将第一个参数作为this,后面的参数作为执行参数。
// 执行时，会将其先绑定到context上，执行完毕后删除。
// TODO 如果传入的是function，实际的context会返回什么呢，要查试一下
function _call(context, ...args) {
	let key = new Symbol(1);
	if (context == null || (typeof(context) != 'object' && typeof(context) != 'function'))
		context = window;
	context[key] = this;
	let result = context[key](...args);
	delete context[key];
	return result;
}
// apply:
// 与call基本一致, 只是参数调整一下。
function _apply(context, args = []) {
	let key = new Symbol(1);
	if (context == null || (typeof(context) != 'object' && typeof(context) != 'function'))
		context = window;
	context[key] = this;
	let result = context[key](...args);
	delete context[key];
	return result;
}
// bind:
// bind会返回一个方法，该方法执行时按this逻辑。
// 另外，方法如果new 时，对象要以原函数的原型链为主。
function _bind(context, ...args) {
	let func = this;
	let fNOP = function(){};
	let bindFunc = function (...sndargs) {
		let params = [...args, ...sndargs];
		return func.call(this instanceof bindFunc ? this : context, ...params);
	}
	fNOP.prototype = this.prototype;
	bindFunc.prototype = new fNOP();
	return bindFunc;
}