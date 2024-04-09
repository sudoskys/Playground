class ReClass {
    private myProp: string;

    constructor(myProp: string) {
        this.myProp = myProp;
    }
}

const reInstance = new ReClass('Hello, world!');

// 使用 Reflect API 获取私有属性的值
const value = Reflect.get(reInstance, 'myProp');
console.log(value); // Output: Hello, world!