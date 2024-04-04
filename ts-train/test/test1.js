// 学生类型
var Student = /** @class */ (function () {
    function Student(firstName, middleInitial, lastName) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.fullName = firstName + "" + middleInitial + "" + lastName;
    }
    return Student;
}());
function greeter(person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}
// let user = {firstName: "Jane", lastName: "User"};
var users = new Student("Jane", "M.", "Users");
var hello = "Hello World---!";
console.log(hello);
document.body.innerHTML = greeter(users);
//# sourceMappingURL=test1.js.map