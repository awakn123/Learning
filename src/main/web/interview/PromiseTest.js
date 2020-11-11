var req1 = new Promise((resolve, reject) =>{
	resolve(1);
});
var req2 = new Promise((resolve, reject) =>{
	resolve(2);
});
var req3 = new Promise((resolve, reject) =>{
	resolve(3);
});
// generator
function* gen(){
	yield req1;
	yield req2;
	yield req3;
}

gen = gen();
function exec() {
	do {
		let res = gen.next();
		console.log(res);
	} while(!res.done)
}
