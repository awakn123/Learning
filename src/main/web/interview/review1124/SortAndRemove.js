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

let set = new Set();
let newUsers = users.filter((user) => {
	if (set.has(user.id))
		return false;
	set.add(user.id);
	return true;
});
console.log(newUsers);