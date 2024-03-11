// Base64
import {logger} from "../logger";

function base64Encode<T extends string | number>(data: T): string {
    return btoa(data.toString());
}


let log = new logger();

let b64 = base64Encode("Hello, world!"); // Output: SGVsbG8sIHdvcmxkIQ==
log.info(b64);


let lines = `121
122
123`;
let md5 = require('md5');
let hash = md5(lines);
log.info(`MD5 Lib Result ${hash}`);
// 模板字符串
// assert 只检查是否为真

// SHA1
// log.info(`SHA1 Result ${sha1}`);


// URL ENCODE

function urlEncode<T extends string | number>(data: T): string {
    return encodeURIComponent(data.toString());
}

let url = urlEncode("Hello, world!");
console.log(url); // Output: Hello%2C%20world%21


// URL DECODE

function urlDecode<T extends string | number>(data: T): string {
    return decodeURIComponent(data.toString());
}

let urlDecoded = urlDecode(url);
console.log(urlDecoded); // Output: Hello, world!

// URL Parse 解析 URL 的字段，查询域名，路径，查询参数等

function urlParse<T extends string | number>(url: T): URL {
    return new URL(url.toString());
}

let parsedUrl = urlParse('https://www.google.com/search?q=nodejs');
console.log(parsedUrl.hostname); // Output: www.google.com




