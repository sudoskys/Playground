let message;
message = 'abc';


console.log(message.endsWith('c'))


console.log("6" + 1); // "61"
console.log("6" - 1); // 5
console.log("6" / 2); // 3
console.log("6" * "2"); // 12


let str = "123";
let num = Number(str); // becomes a number 123

console.log(typeof num); // "number"

let age = Number("an arbitrary string instead of a number"); // NaN, conversion failed
console.log(age); // NaN, conversion failed

console.log(Boolean("0")); // true

let answer = prompt("What’s the “official” name of JavaScript?")
if (answer === "ECMAScript") {
    console.log("Right!")
} else {
    console.log("You dont know? ECMAScript!")
}


function showMessage() {
    console.log('Hello everyone!');
}

let input_ = "Which you want?"
// Object
let user = {
    name: "S", age: 3000, [input_]: 5,
}
console.log(user.name)
console.log(user)

function makeUid(random_seeds) {
    random_seeds = Math.random();
    return {
        random_seeds,
    }
}

