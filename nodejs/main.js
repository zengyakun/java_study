var fs = require('fs');

// 1.阻塞
// var data = fs.readFileSync('input.txt');
//
// console.log(data.toString());
// console.log("程序执行结束！");

// 2.非阻塞
// fs.readFile("input.txt", function (err, data) {
//     if(err) return console.error(err);
//     console.log(data.toString());
// });
//
// console.log("程序执行结束！")

// var events = require("events");
// var eventEmitter = new events.EventEmitter();

console.log(__filename)
console.log(__dirname)

function printHello() {
    console.log('Hello World')
}
var t = setTimeout(printHello, 100);

clearTimeout(t);

setInterval(printHello, 200);
