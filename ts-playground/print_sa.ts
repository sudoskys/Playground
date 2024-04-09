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

// 编译前
enum Color {
    Red,     // 0
    Green,   // 1
    Blue     // 2
  }

  
// a 的类型被推断为 (number | boolean)[]
let b = [1, true];
//上面示例中，变量a的值其实是一个元组，但是 TypeScript 会将其推断为一个联合类型的数组，即a的类型为(number | boolean)[]。所以，元组必须显式给出类型声明。
//元组成员的类型可以添加问号后缀（?），表示该成员是可选的。
let a:[number, number?] = [1];

type Tuple = [string, number, Date];
type TupleEl = Tuple[number];  // string|number|Date
// 上面示例中，Tuple[number]表示元组Tuple的所有数值索引的成员类型，所以返回string|number|Date，即这个类型是三种值的联合类型。

type t = readonly [number, string]
type T = 'a'|'b'|'c';
let foo = 'a';

// let bar:T = foo; // 报错
let bar2:T = foo as T; // 正确

// 交叉类型
const p0:{ x: number } =
  { x: 0, y: 0 } as { x: number };

//  import { type A, a } from './a';

// 1. 基本类型
let isDone: boolean = false;
let age: number = 42;

// 2. 数组类型
let list1: number[] = [1, 2, 3];
let list2: Array<string> = ["a", "b", "c"];

let c: Color = Color.Green;

// 4. 函数类型
function add(x: number, y: number): number {
  return x + y;
}

// 5. 接口
interface Rect {
  width: number;
  height: number;
}
let rect: Rect = { width: 100, height: 50 };

// 6. 类
class Person {
  constructor(public name: string, private age: number) {}
  introduce(): string {
    return `Hello, my name is ${this.name}, I'm ${this.age} years old.`;
  }
}
let jordan = new Person("Jordan", 42);


// 1. 可选参数和可选属性
function greet(name?: string) {
    return name ? `Hello, ${name}!` : "Hello!";
}

interface Config {
    height: number;
    width?: number;  // 可选属性
}

const config: Config = { height: 100 };

// 2. 非空断言 (x!)
function printLength(str: string | undefined | null) {
    let strLength: number = str!.length;  // We're sure str is not null nor undefined
    console.log(strLength);
}

// 3. const 声明
const pi: number = 3.14159;

// 4. 类型断言
let someValue: any = "this is a string";
let strLength: number = (<string>someValue).length;  // 类型断言方式一
let strLength2: number = (someValue as string).length;  // 类型断言方式二（在 JSX 语法中只允许这种方式）

// 5. 闭包
function outerFunction(outVar: string) {
    return function innerFunction(inVar: string) {
        console.log(outVar);
        console.log(inVar);
    }
}

let newFunction = outerFunction("outside");
newFunction("inside"); // 输出: outside, inside

function countInstances(value:any, context:any) {
    let instanceCount = 0;
  
    const wrapper = function (...args:any[]) {
      instanceCount++;
      const instance = new value(...args);
      instance.count = instanceCount;
      return instance;
    } as unknown as typeof YOUClass;
  
    wrapper.prototype = value.prototype; // A
    return wrapper;
  }
  
@countInstances
class YOUClass {
    count?: number;
}
  
const inst1 = new YOUClass();
inst1 instanceof YOUClass // true
inst1.count // 1

console.log(Reflect.get(inst1, 'count')); //输出：1
console.log(Reflect.has(inst1, 'count')); //输出：true
console.log(Reflect.ownKeys(inst1)); //输出：["count"]
