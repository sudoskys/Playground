class MyClass {
    [index: string]: any; // 添加索引签名
    name: string;
    age: number;

    constructor(name: string, age: number) {
        this.name = name;
        this.age = age;
    }
}

const myInstance = new MyClass('John', 25);

for (let key of Object.keys(myInstance)) {
    console.log(key, myInstance[key]); // 需要索引签名
}

// 将类实例转换为 JSON 字符串
const jsonString = JSON.stringify(myInstance);
console.log(jsonString);