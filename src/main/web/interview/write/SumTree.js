//1
//2 3
//4 5
//从根到叶子共 3 条：1->2->4, 1->2->5, 1->3
//构成的数字为 124，125，13，求和 124 + 125 + 13 = 262 即为所求

function Node(v) {
	this.value = v;
	this.left = null;
	this.right = null;
}

let root = new Node(1);
root.left = new Node(2);
root.left.left = new Node(4);
root.left.right = new Node(5);
root.right = new Node(3);

function sumTree(node, sum = 0) {
	sum = node.value + sum;
	if (!node.left && !node.right){
		return sum;
	}
	let leftValue = 0, rightValue = 0;
	sum *= 10;
	if (node.left) {
		leftValue = sumTree(node.left, sum);
	}
	if (node.right) {
		rightValue = sumTree(node.right, sum);
	}
	return leftValue + rightValue;
}

sumTree(root);