// console.log('程序开始执行：')

// var counter = 10;
// console.log('计数：%d', counter)
//
// console.time('获取数据')
//
// console.timeEnd('获取数据')
//
// console.info('程序执行完毕。')


// process.on('exit', function (code) {
//     setTimeout(function () {
//         console.log('该代码不会执行')
//     }, 0);
//
//     console.log('退出码为：', code);
// })
//
// console.log('程序执行结束。')


process.stdout.write('hello world' + '\n');
process.argv.forEach(function (val, index, array) {
    console.log(index + ': ' + val);
});

console.log(process.execPath);

console.log(process.platform);

// 输出当前目录
console.log('当前目录: ' + process.cwd());

// 输出当前版本
console.log('当前版本: ' + process.version);

// 输出内存使用情况
console.log(process.memoryUsage());