import * as fs from 'fs';

// 创建一个写入流
let writeStream: fs.WriteStream = fs.createWriteStream('output.txt', 'utf-8');

// 写入数据到流中
writeStream.write("Hello, ");
writeStream.write("World!");

// 标记文件写入完成 
writeStream.end();

// 创建一个读取流
let readStream: fs.ReadStream = fs.createReadStream('output.txt', 'utf-8');

// 处理流事件
readStream.on('data', function(chunk: string) {
    console.log(chunk);
});


// import * as fs from 'fs';

let data: string = "Hello, World!";

// 异步写文件
fs.writeFile('output.txt', data, 'utf-8', (err: NodeJS.ErrnoException|null) => {
    if (err) throw err;
    console.log('The file has been saved!');
});

// 异步读文件
fs.readFile('output.txt', 'utf-8', (err: NodeJS.ErrnoException|null, data: string) => {
    if (err) throw err;
    console.log(data);
});