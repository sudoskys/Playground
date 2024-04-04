// 布尔值的表示
var isDone = false;
// 数字的表示
var decLiteral = 6;
var hexLiteral = 0xf00d;
var binaryLiteral = 10;
var octalLiteral = 484;
// 重定义
var person_name = "bob";
person_name = "smith";
// 模板字符串
var body_name = "Gene";
var age = 37;
var sentence = "Hello, my name is ".concat(body_name, ".\n\nI'll be ").concat(age + 1, " years old next month.");
console.log(sentence);
// 列表
var list_number = [1, 2, 3];
// 泛型
var list_array = [1, 2, 3];
//  元组设置
var x;
x = ['hello', 10]; // OK
// 枚举
var Color;
(function (Color) {
    Color[Color["Red"] = 0] = "Red";
    Color[Color["Green"] = 1] = "Green";
    Color[Color["Blue"] = 2] = "Blue";
})(Color || (Color = {}));
var c = Color.Green;
// 据说有这种映射呢
// let colorName: string = Color[2];
// alert(colorName);  // 显示'Green'因为上面代码里它的值是2
// Const
var a = 1;
// a = 2
// 不检查
var notSure = 4;
notSure = "maybe a string instead";
notSure = false; // okay, definitely a boolean
notSure.ifItExists(); // okay, ifItExists might exist at runtime
notSure.toFixed(); // okay, toFixed exists (but the compiler doesn't check)
var prettySure = { 'name': 'wxx' };
// prettySure.toFixed(); // Error: Property 'toFixed' doesn't exist on type 'Object'.
// any 类型的应用
var list = [1, true, "free"];
list[1] = 100;
// return void
function warnUser() {
    alert("wut are u doing?");
}
// undefined
var unuseable = undefined;
// null
var n = null;
// Never
// 返回never的函数必须存在无法达到的终点
function error(message) {
    throw new Error(message);
}
// 推断的返回值类型为never
function fail() {
    return error("Something failed");
}
// 类型断言 这....
var someValue = "this is a string";
var strLength = someValue.length;
//
function f() {
    var message = "Hello, world!";
    message.slice(1);
    return message;
}
// 莫名其妙的返回
function fs() {
    var a = 10;
    return function g() {
        return a + 1;
    };
}
var g = fs();
g();
//# sourceMappingURL=feat.js.map