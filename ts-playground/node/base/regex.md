1. 创建正则表达式：

```typescript
// 使用字面量创建正则表达式
let regex1 = /abc/;

// 使用 RegExp 构造函数创建正则表达式
let regex2 = new RegExp('abc');
```

2. 使用 `test` 方法检查字符串是否匹配正则表达式：

```typescript
let regex = /abc/;
console.log(regex.test('abcdef')); // Output: true
console.log(regex.test('abcdefabc')); // Output: true
console.log(regex.test('abcdefg')); // Output: true
console.log(regex.test('acbdef')); // Output: false
```

3. 使用 `exec` 方法查找匹配的字符串：

```typescript
let regex = /abc/;
let result = regex.exec('abcdefabc');
console.log(result); // Output: ['abc', index: 0, input: 'abcdefabc', groups: undefined]
```

4. 使用 `match` 方法查找所有匹配的字符串：

```typescript
let regex = /abc/g; // 注意，我们需要使用 "g" 标志来查找所有匹配
let result = 'abcdefabc'.match(regex);
console.log(result); // Output: ['abc', 'abc']
```

5. 使用括号创建捕获组：

```typescript
let regex = /(abc)(def)/;
let result = regex.exec('abcdef');
console.log(result); // Output: ['abcdef', 'abc', 'def', index: 0, input: 'abcdef', groups: undefined]
```

6. 使用方括号创建字符集：

```typescript
let regex = /[abc]/g;
let result = 'abcdefabc'.match(regex);
console.log(result); // Output: ['a', 'b', 'c', 'a', 'b', 'c']
```

7. 使用预定义的字符类：

```typescript
let regex = /\d/g; // 匹配所有数字
let result = 'abc123def456'.match(regex);
console.log(result); // Output: ['1', '2', '3', '4', '5', '6']
```

8. 使用量词：

```typescript
let regex = /a{3}/; // 匹配三个连续的 "a"
console.log(regex.test('aaa')); // Output: true
console.log(regex.test('aa')); // Output: false
```

这只是正则表达式的一部分功能，正则表达式还有许多其他的特性和用法。
