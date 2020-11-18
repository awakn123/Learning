/**
 * 当我们遇到const fn1 = x => x*2 ,  fn2 = x => x+3 , fn3 = x =>x-1 ,
 * 想要把处理数据的函数像管道一样连接起来， 然后让数据穿过管道得到最终的结果,如果不通过compose函数处理，
 * 则是fn3(fn2(fn1(x))),这样看起来非常不美观。因此我们可以构建一个compose函数，
 * 它接受任意多个函数作为参数（这些函数都只接受一个参数）
 * compose函数为从右向左执行。
 */
function compose(...args){
	return function(x){
		let len = args.length;
		if(args == 0) return x;
		if(len == 1) return args[0](x);
		return args.reduceRight((pre,cur)=>{
			return cur(pre)
		},x)
	}
}
const fn1 = x => x*2 , fn2 = x => x+3 , fn3 = x =>x-1;
const fn4 = compose(fn1,fn2,fn3);
console.log(fn4(5))

// redux中的compose源码
function compose(...funcs) {
	if (funcs.length === 0) {
		return arg => arg
	}
	if (funcs.length === 1) {
		return funcs[0]
	}
	return funcs.reduce((a, b) => (...args) => a(b(...args)))
}

// 拆解一下redux的compose
function compose(...funcs) {
	if (funcs.length === 0) {
		return arg => arg
	}
	if (funcs.length === 1) {
		return funcs[0]
	}
	return funcs.reduce((acc, cur) => {
		return (...args) => {
			let res = cur(...args);
			console.log(acc, cur, res);
			return acc(res);
		}
	})
}

// 我们可以这样理解：
// reduce执行时，先取0,1 function，此时返回了一个方法，此方法会先执行1,再执行0。
// 然后该方法 acc与第2个function进入下轮循环，此时又返回了一个方法（与上面的方法定义相同），此方法会先执行2，再执行acc
// 依次循环，形成一个0(1(2(value)))的执行顺序
// 而写法就是funcs.reduce((a,b)=> (...args) => a(b(...args)));