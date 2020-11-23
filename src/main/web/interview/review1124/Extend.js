/**
 javascript并没有继承，只有原型链委托这一种形式。所以，我们要实现继承的语法，需要明确继承有哪些场景需要处理：
 1. 父类方法 - 通过原型链委托
 2. 构造函数与父类构造函数
 3. constructor 方法
 */
// 继承的模拟实现
// 父类
function Person(name) {
	this.name = name;
	this.age = 0;
}
Person.prototype.setAge = function(age){
	this.age = age;
}

// 1. 直接继承prototype
function Student(name, grade) {
	this.name = name;
	this.grade = grade;
	this.age = 0;
	this.sex = '';
}
Student.prototype = Person.prototype;
Student.prototype.setSex = function(sex) {
	this.sex = sex;
}
// 缺点很明显，不能共用构造函数
// 对student的prototype的修改也会反映到Person上。

// 2. 子类的prototype使用对象
function Student(name, grade) {
	this.name = name;
	this.grade = grade;
	this.age = 0;
	this.sex = '';
}
Student.prototype = new Person();
Student.prototype.setSex = function(sex) {
	this.sex = sex;
}
// 缺点为不能共用构造函数；

// 3. 构造函数也使用对象
function Student(name, grade) {
	Person.call(this, name);
	this.grade = grade;
	this.sex = '';
}
Student.prototype = new Person();
Student.prototype.setSex = function(sex) {
	this.sex = sex;
}
// 缺点为实例了两遍对象。

// 4. 使用Object.create
function Student(name, grade) {
	Person.call(this, name);
	this.grade = grade;
	this.sex = '';
}
Student.prototype = Object.create(Person.prototype);
Student.constructor = Student;
Student.prototype.setSex = function(sex) {
	this.sex = sex;
}
// 暂时来说最好的方式了

// 总结：对象的原型链分为三部分：构造函数、prototype与constructor
// 使用构造函数时会获得Prototype，但使用Object.create(prototype)则不会调用构造函数。
// constructor需要记着重新赋值。
