// 布尔值的表示
let isDone: boolean = false;

// 数字的表示
let decLiteral: number = 6;
let hexLiteral: number = 0xf00d;
let binaryLiteral: number = 0b1010;
let octalLiteral: number = 0o744;

// 重定义
let person_name: string = "bob";
person_name = "smith";

// 模板字符串
let body_name: string = `Gene`;
let age: number = 37;
let sentence: string = `Hello, my name is ${body_name}.

I'll be ${age + 1} years old next month.`;
console.log(sentence)

// 列表
let list_number: number[] = [1, 2, 3];

// 泛型
let list_array: Array<number> = [1, 2, 3];

//  元组设置
let x: [string, number];
x = ['hello', 10]; // OK

// 枚举
enum Color {Red = 0, Green, Blue = 2}

let c: Color = Color.Green;

// 据说有这种映射呢
// let colorName: string = Color[2];
// alert(colorName);  // 显示'Green'因为上面代码里它的值是2

// Const
const a = 1;
// a = 2

// 不检查
let notSure: any = 4;
notSure = "maybe a string instead";
notSure = false; // okay, definitely a boolean

notSure.ifItExists(); // okay, ifItExists might exist at runtime
notSure.toFixed(); // okay, toFixed exists (but the compiler doesn't check)

let prettySure: Object = {'name': 'wxx'};

// prettySure.toFixed(); // Error: Property 'toFixed' doesn't exist on type 'Object'.

// any 类型的应用
let list: any[] = [1, true, "free"];
list[1] = 100;

// return void
function warnUser(): void {
    alert("wut are u doing?");
}

// undefined
let unuseable: void = undefined

// null
let n: null = null;

// Never
// 返回never的函数必须存在无法达到的终点
function error(message: string): never {
    throw new Error(message);
}

// 推断的返回值类型为never
function fail() {
    return error("Something failed");
}

// 类型断言 这....
let someValue: any = "this is a string";
let strLength: number = (someValue as string).length;

//
function f() {
    var message = "Hello, world!";
    message.slice(1)
    return message;
}
