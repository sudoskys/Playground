// 打印平行四边形
for (let i = 0; i < 5; i++) {
    let str = '';
    for (let j = 0; j < 5 - i; j++) {
        str += ' ';
    }
    for (let k = 0; k < 5; k++) {
        str += '*';
    }
    console.log(str);
}
console.log('-------------------');
//漏斗倒三角
for (let i = 0; i < 5; i++) {
    let str = '';
    for (let j = 0; j < i; j++) {
        str += ' ';
    }
    for (let k = 0; k < 5 - i; k++) {
        str += '*';
    }
    console.log(str);
}