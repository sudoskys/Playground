let message: string = 'Hello, World!';
let buffer: Buffer = Buffer.from(message);
let encodedMessage: string = buffer.toString('base64');

console.log(encodedMessage); // 输出一串 Base64 编码的数据

let decodedBuffer: Buffer = Buffer.from(encodedMessage, 'base64');
let decodedMessage: string = decodedBuffer.toString();
console.log(decodedMessage); // 输出 'Hello, World!'