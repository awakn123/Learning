//const chain = new Chain();
//chain.eat().sleep(5).eat().sleep(6).work();
//chain.eat()
//chain.work();
//eat -> wait 5s -> eat -> wait 6s -> work

function Chain() {

}
Chain.prototype.eat = function() {
	console.log("eat");
	return this;
}

Chain.prototype.sleep = function(time) {
	let startTime = new Date();
	let delay = time * 1000;
	while (new Date() - startTime < delay){}
	return this;
}
Chain.prototype.work = function(){
	console.log("work");
	return this;
}
//---------------
function Chain() {
	this.time = 0;
}
Chain.prototype.eat = function() {
	setTimeout(() => console.log("eat"), this.time * 1000);
	return this;
}

Chain.prototype.sleep = function(time) {
	this.time += time;
	return this;
}
Chain.prototype.work = function(){
	setTimeout(()=>console.log("work"), this.time * 1000);
	return this;
}