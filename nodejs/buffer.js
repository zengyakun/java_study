// test1
// buf = Buffer.alloc(256);
//
// len = buf.write("www.runoob.com");
//
// console.log("写入字节数：" + len);

// test2
// buf = Buffer.alloc(26);
//
// for (var i = 0; i < 26; i++) {
//     buf[i] = i + 97;
// }
//
// console.log(buf.toString('ascii'));
// console.log(buf.toString('ascii', 0, 5));
// console.log(buf.toString('utf8', 0, 5));
// console.log(buf.toString(undefined, 0, 5));

// test3
const buf = Buffer.from([0x1, 0x2, 0x3, 0x4, 0x5]);
const json = JSON.stringify(buf);
console.log(json);

const copy = JSON.parse(json, (key, value) => {
    return value && value.type === 'Buffer' ?
        Buffer.from(value.data) : value;
});

console.log(copy);
