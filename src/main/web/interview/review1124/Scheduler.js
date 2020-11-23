function Scheduler(num) {
	this.list = [];
	this.count = 0;
	this.num = num;

}
Scheduler.prototype.add = async function(fn) {
	(this.count >= this.num) && await new Promise((resolve) => this.list.push(resolve));
	this.count++;
	let result = await fn();
	this.count--;
	if (this.list.length > 0) {
		this.list.shift()();
	}
	return result;
}

function delay (key, time) {
	return new Promise((resolve) => {
		setTimeout(() => resolve(key), time)
	});
}

var sc = new Scheduler(2);
var startTime = new Date().getTime();
sc.add(() => delay(1, 1000)).then((key) => console.log(`${key}:${new Date().getTime() - startTime}`));
sc.add(() => delay(2, 1000)).then((key) => console.log(`${key}:${new Date().getTime() - startTime}`));
sc.add(() => delay(3, 1000)).then((key) => console.log(`${key}:${new Date().getTime() - startTime}`));
sc.add(() => delay(4, 1000)).then((key) => console.log(`${key}:${new Date().getTime() - startTime}`));
sc.add(() => delay(5, 1000)).then((key) => console.log(`${key}:${new Date().getTime() - startTime}`));
