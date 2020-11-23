/**
 题目描述：实现一个CodingMan，可以按照以下方式调用：
 CodingMan(‘Hank’)
 输出：
 Hi！This is Hank!

 CodingMan(‘Hank’).sleep(10).eat(‘dinner’)
 输出：
 Hi！This is Hank!
 //等待10秒
 Wake up after 10
 Eat dinner~

 CodingMan(‘Hank’).eat(‘dinner’).eat(‘supper’)
 输出：
 Hi！This is Hank!
 Eat dinner~
 Eat supper~

 CodingMan(‘Hank’).sleepFirst(5).eat(‘supper’)
 输出：
 //等待5秒
 Wake up after 5
 Hi！This is Hank!
 Eat supper~
 */
function CodingMan(name) {
	function Man(name) {
		this.name = name;
		setTimeout(() => {
			console.log(`Hi! This is ${name}!`);
		}, 0)
	}

	Man.prototype.sleep = function(time){
		let curTime = new Date();
		setTimeout(() => {
			while (new Date() - curTime < time * 1000) {}
			console.log(`Wake up after ${time}`);
		}, 0)
		return this;
	}

	Man.prototype.sleepFirst = function(time) {
		let curTime = new Date();
		while(new Date() - curTime < time * 1000) {}
		console.log(`Wake up after ${time}`);
		return this;
	}

	Man.prototype.eat = function(food){
		setTimeout(() => console.log(`Eat ${food}~`), 0);
		return this;
	}

	return new Man(name);
}

CodingMan("Hank");
//Hi! This is Hank!
CodingMan("Hank").sleep(10).eat("dinner");
//Hi! This is Hank!
//10s
//Wake up after 10
//Eat dinner~
CodingMan("Hank").sleepFirst(5).eat("dinner").eat("supper");
// 5s
//Wake up after 5
//Hi! This is Hank!
//	Eat dinner~
//	Eat supper~

// -------------number 2---------------
function CodingMan(name) {
	function Man(name) {
		this.name = name;
		this.sleepTime = 0;
		setTimeout(() => {
			console.log(`Hi! This is ${name}!`);
		}, 0)
	}

	Man.prototype.sleep = function(time){
		this.sleepTime = time;
		let curTime = new Date();
		setTimeout(() => {
			while (new Date() - curTime < time * 1000) {}
			console.log(`Wake up after ${time}`);
		}, this.sleepTime)
		return this;
	}

	Man.prototype.sleepFirst = function(time) {
		let curTime = new Date();
		while(new Date() - curTime < time * 1000) {}
		console.log(`Wake up after ${time}`);
		return this;
	}

	Man.prototype.eat = function(food){
		setTimeout(() => console.log(`Eat ${food}~`), this.sleepTime);
		return this;
	}

	return new Man(name);
}