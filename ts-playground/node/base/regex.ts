// 使用字面量创建正则表达式
let regex1 = /abc/;

// 使用 RegExp 构造函数创建正则表达式
let regex2 = new RegExp('abc');

let regex = /abc/;
console.log(regex.test('abcdef')); // Output: true
console.log(regex.test('abcdefabc')); // Output: true
console.log(regex.test('abcdefg')); // Output: true
console.log(regex.test('acbdef')); // Output: false


let result = regex.exec('abcdefabc');
console.log(result); // Output: ['abc', index: 0, input: 'abcdefabc', groups: undefined]


let regex3 = /abc/g; // 注意，我们需要使用 "g" 标志来查找所有匹配
let result2 = 'abcdefabc'.match(regex3);
console.log(result2); // Output: ['abc', 'abc']

let regex4 = /(abc)(def)/;
let result4 = regex4.exec('abcdef');
console.log(result4); // Output: ['abcdef', 'abc', 'def', index: 0, input: 'abcdef', groups: undefined]