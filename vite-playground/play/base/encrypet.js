"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Base64
var index_ts_1 = require("../logger/index.ts");
function base64Encode(data) {
    return btoa(data.toString());
}
var log = new index_ts_1.logger();
var b64 = base64Encode("Hello, world!"); // Output: SGVsbG8sIHdvcmxkIQ==
log.info(b64);
var lines = "121\n122\n123";
var md5 = require('md5');
var hash = md5(lines);
log.info("MD5 Lib Result ".concat(hash));
// 模板字符串
// assert 只检查是否为真
// SHA1
// log.info(`SHA1 Result ${sha1}`);
// URL ENCODE
function urlEncode(data) {
    return encodeURIComponent(data.toString());
}
var url = urlEncode("Hello, world!");
console.log(url); // Output: Hello%2C%20world%21
// URL DECODE
function urlDecode(data) {
    return decodeURIComponent(data.toString());
}
var urlDecoded = urlDecode(url);
console.log(urlDecoded); // Output: Hello, world!
// URL Parse 解析 URL 的字段，查询域名，路径，查询参数等
function urlParse(url) {
    return new URL(url.toString());
}
var parsedUrl = urlParse('https://www.google.com/search?q=nodejs');
console.log(parsedUrl.hostname); // Output: www.google.com
