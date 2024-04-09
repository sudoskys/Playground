type optional_email = string | null;

class User {
    private _email: optional_email = null;

    constructor(email: string) {
        this.email = email; // 使用 setter 方法，会触发校验逻辑
    }

    get email(): optional_email {
        return this._email;
    }

    set email(value: string) {
        // 在这里添加校验逻辑
        if (!value.includes('@')) {
            throw new Error('Invalid email');
        }
        this._email = value;
    }
    // 定义一个 toJSON 方法
    toJSON() {
        return {
            email: this._email
        };
    }
}
const handleError = (error: any) =>
    error instanceof Error
        ? console.error(error.message || error)
        : console.error('Caught an exception that is not an instance of Error');

try {
    let user = new User('test@example.com');
    console.log(user.email); // Output: test@example.com
    user.email = 'invalid'; // 抛出错误：Invalid email
} catch (error) {
    handleError(error);
}

let user = new User('test@example.com');
let json = JSON.stringify(user);
console.log(json); // Output: {"_email":"test@example.com"}