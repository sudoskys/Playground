import {md5} from 'hash-wasm';

// Base64
function base64Encode<T extends string | number>(data: T): string {
    return btoa(data.toString());
}


let b64 = base64Encode("Hello, world!"); // Output: SGVsbG8sIHdvcmxkIQ==
console.info(b64);


let globalHash: string | null = null;

let lines = `121
122
123`;

md5(lines).then((hash) => {
    console.log(`MD5 Result ${hash}`);
    globalHash = hash;
}).then(() => {
    // 在这里，globalHash 已经被赋值，所以你可以使用它
    console.log(`Global hash: ${globalHash}`);
});
/*在 `then` 方法的回调函数中返回一个值是有用的。这个值会被用作下一个 `then` 方法的输入。也就是说，你可以在一个 `then` 方法的回调函数中返回一个值，然后在下一个 `then` 方法的回调函数中使用这个值。
 */

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




