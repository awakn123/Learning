//request = (url: string) => Promise
//const urls = [ url1, url2, ..., urln ]
//1. 串行发送
//2. 并行发送
//3. 可控并行 max = 5

let request = (url) => {
	return function() {
		return new Promise(resolve => {
			setTimeout(() => {
				console.log(url);
				resolve();
			}, 1000)
		});
	}
}

let urls = ["url1", "url2", "url3"].map(request);

// 串行 1
function run() {
	if(urls.length != 0) {
		let req = urls.shift();
		req().then(() => {
			run();
		})
	}
}

// 串行 2
async function run2() {
	for (let i =0; i<urls.length; i++) {
		await(urls[i]());
	}
}

// 并行 1
function syncRun() {
	return Promise.all(urls.map((req) => req()));
}

// 并行 2
function syncRun2() {
	return new Promise((resolve, reject) => {
		let num = urls.length, arr = new Array(num), idx = 0;
		for (let i = 0; i < num; i++) {
			urls[i]().then((data) => {
				arr[i] = data;
				idx++;
				if (idx == num) {
					resolve(arr);
				}
			}).catch((err) => {
				reject(err);
			})
		}
	})
}
// 并行限制
function Scheduler(num) {
	this.num = num;
	this.count = 0;
}
Scheduler.prototype.run = function(arr) {
	while (this.count < this.num && arr.length > 0) {
		let req = arr.shift();
		this.count++;
		req().then(() => {
			this.count--;
			this.run(arr);
		})
	}
}

let scheduler = new Scheduler(2);
scheduler.run(urls);

// 并行限制2
async function scheduler2Run(arr){
	let k = 2,cnt = 0, resolveArr = [];
	while(arr.length > 0) {
		if (cnt == k) {
			await new Promise(resolve => resolveArr.push(resolve));
		}
		cnt++;
		arr.shift()().then(function(){
			cnt--;
			resolveArr.length > 0 && resolveArr.shift()();
		});
	}
}
scheduler2Run(urls);