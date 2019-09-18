var util = require('util');
// function Base() {
//     this.name = 'base';
//     this.base = 1991;
//     this.sayHello = function () {
//         console.log('Hello ' + this.name)
//     };
// }
//
// Base.prototype.showName = function () {
//     console.log(this.name);
// }
//
// function Sub() {
//     this.name = 'sub';
// }
//
// util.inherits(Sub, Base);
// var objBase = new Base();
// objBase.showName();
// objBase.sayHello();
// console.log(objBase);
//
// var objSub = new Sub();
// objSub.showName();
// // objSub.sayHello();
// console.log(objSub);
//
// console.log('----------------inspect----------------')
// function Person() {
//     this.name = 'byvoid';
//     this.toString = function () {
//         return this.name;
//     }
// }
// var obj = new Person();
// console.log(util.inspect(obj));
// console.log(util.inspect(obj, true));

console.log('----------------isArray----------------')
console.log(util.isArray([]));
console.log(util.isArray(new Array()));
console.log(util.isArray({}));

console.log('----------------isRegExp----------------')
console.log(util.isRegExp(/some regexp/));
console.log(util.isRegExp(new RegExp('anothor regexp')));
console.log(util.isRegExp({}));

console.log('----------------isDate----------------')
console.log(util.isDate(new Date()));
console.log(util.isDate(Date()));
console.log(util.isDate({}));

console.log('----------------isError----------------')
console.log(util.isError(new Error()));
console.log(util.isError(new TypeError()));
console.log(util.isError({ name: 'Error', message: 'an error occurred' }));

