// 没有延时的时候，then是不是立刻执行的呢？是立刻执行的。
setTimeout(function(){
	console.log("timeout");
}, 0)
new Promise(function(resolve){
	console.log("promise");
	resolve();
}).then(() => {
	console.log("then");
});
// promise -> then -> timeout.

// then虽然是立刻执行的，但却会在主堆栈之后
setTimeout(function(){
	console.log("timeout");
}, 0)
new Promise(function(resolve){
	console.log("promise");
	resolve();
}).then(() => {
	console.log("then");
});
console.log("main frame");
// promise -> main frame -> then -> timeout.
// 原因：
// 1。setTimeout 最小执行时间为4ms.
// 2. promise中的resolve以microtask运行，它的优先级比macrotask要高。
// mdn的解释是microtask 会在创建它的方法或程序退出且javascript执行stack为空时执行，但比event loop执行消息队列要早。
// 这个microtask后面还可以继续读。https://developer.mozilla.org/en-US/docs/Web/API/HTML_DOM_API/Microtask_guide
// 我们可以使用 queueMicrotask(function)来自主添加Microtask，另外，MutationObserver API也使用microtask.

// requestAnimationFrame，在有进程卡住的时候，会先执行呢，还是会后执行呢，还是会放弃执行呢？
let count = 0, rafStartTime = new Date();
function raf(){
	if (count < 10) {
		count++;
		console.log("requestAnimationFrame", new Date() - rafStartTime);
		rafStartTime = new Date();
		requestAnimationFrame(raf);
	}
}
requestAnimationFrame(raf)

function sync() {
	let startTime = new Date();
	while(new Date() - startTime < 30){};
	console.log("sync");
}
sync();
sync();
setTimeout(sync, 0);
setTimeout(sync, 30);
// sync -> sync -> raf*2 -> sync -> raf -> sync -> raf * 7.
// sync -> sync -> raf 61 -> sync -> raf 33 -> sync -> raf 30 -> raf 1 -> raf 9 -> raf 16...
// sync -> sync -> raf 60 -> raf 1 -> sync -> raf 31 -> sync -> raf 30 -> raf 0 -> raf 11 -> raf 16...
// 可知，直接执行的方法会堵塞渲染，在堵塞之后会立刻刷新（比timeout的优先级要高），有时会连刷两次（可能是浏览器的刷新机制有所保证），然后进入timeout的frame后又会进入堵塞状态。

async function async1(){
	console.log('async1 start');
	await async2();
	console.log('async1 end');
}
async function async2(){
	console.log('async2');
}
console.log('script start');
setTimeout(function(){
	console.log('setTimeout');
},0);
requestAnimationFrame(function(){
	console.log('requestAnimationFrame');
});
async1();
new Promise(function(resolve){
	console.log('promise1');
	resolve();
}).then(function(){
	console.log('promise2');
});
console.log('script end');
// script start -> async1 start -> async2 -> promise1 -> script end -> async1 end -> promise2 -> raf -> setTimeout.
// 实际执行中，raf总是比setTimeout先执行。
// 查到的资料：第一次触发requestAnimationFrame的时机在不同浏览器也存在差异，Edge中，大概16.7ms之后触发，而Chrome则立即触发，跟setImmediate差不多
// 但实际上在我的（Chronium版本）edge中的raf同样比setTimeout要早。
// IE中则是有先有后，看来确实不同浏览器实现不同。
// mdn中讲的是，Edge versions below 17 and Internet Explorer do not reliably fire requestAnimationFrame before the paint cycle.
// 也可能是版本问题，正常的就应该是Chrome的效果？

// setImmediate 应该只能在IE或Edge(非Chrome内核）中用。