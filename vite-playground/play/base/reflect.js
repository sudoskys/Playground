var MyClass = /** @class */ (function () {
    function MyClass(myProp) {
        this.myProp = myProp;
    }
    return MyClass;
}());
var myInstance = new MyClass('Hello, world!');
// 使用 Reflect API 获取私有属性的值
var value = Reflect.get(myInstance, 'myProp');
console.log(value); // Output: Hello, world!
