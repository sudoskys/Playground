"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.makeUid = void 0;
var pack_ts_1 = require("../pack.ts");
function makeUid(person) {
    return "U_" + person;
}
exports.makeUid = makeUid;
var logger = /** @class */ (function () {
    function logger() {
    }
    logger.prototype.info = function (data) {
        console.log(data);
        return null;
    };
    return logger;
}());
function WorkWithBot(bot) {
    console.log("Bot will start with " + bot.bot_id);
    makeUid(bot.bot_token);
}
var bot = { bot_token: "AASS:888", bot_id: 115523 };
WorkWithBot(bot);
var Student = /** @class */ (function () {
    function Student(class_room, name) {
        this.class_room = class_room;
        this.name = name;
        this.full_name = class_room + "_" + name;
    }
    return Student;
}());
var student = new Student("119", "Jane");
var log = new logger();
log.info(student);
function createStudent() {
    return new Student("121", "John");
}
var head = (0, pack_ts_1.pack_head)("122342");
log.info(head);
log.info(createStudent());
var student_d = {};
student_d.bot_token = "10086";
student_d.bot_id = 110;
log.info("Build Bot ".concat(JSON.stringify(student_d)));
function getLocaleString(locale) {
    return "66666";
}
console.log("Hello from union type");
log.info(getLocaleString("email_heading_id"));
function greet(_a) {
    var name = _a.name, age = _a.age;
    console.log("Hello, ".concat(name, ". You are ").concat(age, " years old."));
}
greet({ name: 'Alice', age: 25 }); // Output: Hello, Alice. You are 25 years old.
var UsersDto = /** @class */ (function () {
    function UsersDto() {
        this.id = 0;
        this.username = "";
        this.email = "";
    }
    return UsersDto;
}());
