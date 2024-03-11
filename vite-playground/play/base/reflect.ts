class MyClass {
    private myProp: string;

    constructor(myProp: string) {
        this.myProp = myProp;
    }
}

const myInstance = new MyClass('Hello, world!');

// 使用 Reflect API 获取私有属性的值
const value = Reflect.get(myInstance, 'myProp');
console.log(value); // Output: Hello, world!