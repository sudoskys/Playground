import {pack_head} from "../pack.js"

export function makeUid(person: string) {
    return "U_" + person
}

export interface MakeBot {
    bot_token: string,
    bot_id: number,
}

class logger<T> {
    info(data: T): null {
        console.log(data)
        return null
    }
}

function WorkWithBot<T extends MakeBot>(bot: T) {
    console.log("Bot will start with " + bot.bot_id)
    makeUid(bot.bot_token)
}


const bot = {bot_token: "AASS:888", bot_id: 115523}

WorkWithBot(bot)


class Student {
    full_name: string;

    constructor(public class_room: string, public name: string) {
        this.full_name = class_room + "_" + name
    }
}

const student = new Student("119", "Jane")

let log = new logger()

log.info(student)

function createStudent(): Student {
    return new Student("121", "John");
}

const head = pack_head("122342")
log.info(head)
log.info(createStudent())


let student_d = {} as MakeBot;
student_d.bot_token = "10086"
student_d.bot_id = 110

log.info(`Build Bot ${JSON.stringify(student_d)}`)

type EmailLocaleIDs = "welcome_email" | "email_heading";
type FooterLocaleIDs = "footer_title" | "footer_sendoff";
type AllLocaleIDs = `${EmailLocaleIDs | FooterLocaleIDs}_id`;

function getLocaleString(locale: AllLocaleIDs): string {
    return "66666"
}

console.log("Hello from union type")
log.info(getLocaleString("email_heading_id"))


function greet({name, age}: { name: string; age: number }) {
    console.log(`Hello, ${name}. You are ${age} years old.`);
}

greet({name: 'Alice', age: 25}); // Output: Hello, Alice. You are 25 years old.


class UsersDto {
    id: number = 0;
    username: string = "";
    email: string = "";
}

