// 接口
interface Person {
    firstName: string;
    lastName: string;
}

// 学生类型
class Student {
    fullName: string;

    constructor(public firstName: string,
                public middleInitial: string,
                public lastName: string
    ) {
        this.fullName = firstName + "" + middleInitial + "" + lastName;
    }
}

function greeter(person: Person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}

// let user = {firstName: "Jane", lastName: "User"};

let users = new Student("Jane", "M.", "Users");
var hello = "Hello World---!";
console.log(hello);
document.body.innerHTML = greeter(users);

