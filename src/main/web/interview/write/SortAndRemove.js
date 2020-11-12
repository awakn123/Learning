const users = [
	{
		id: 1,
		name: '张三',
	},
	{
		id: 2,
		name: '张三',
	},
	{
		id: 3,
		name: '李四',
	},
	{
		id: 1,
		name: '李四',
	}
];

users.sort(function(a, b){
	return a.id - b.id;
});
users.sort(function(a, b){
	return a.name.localeCompare(b.name);
});

let map = new Map();
users.forEach((user) => map.has(user.id) || map.set(user.id, user));
console.log(map);