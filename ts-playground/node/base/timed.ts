// 创建一个新的日期对象
let now = new Date();
console.log(now); // 输出当前的日期和时间

// 获取日期的各个部分
console.log(now.getFullYear()); // 输出年份
console.log(now.getMonth() + 1); // 输出月份，注意月份是从 0 开始的，所以我们需要加 1
console.log(now.getDate()); // 输出日期
console.log(now.getHours()); // 输出小时
console.log(now.getMinutes()); // 输出分钟
console.log(now.getSeconds()); // 输出秒

// 设置日期的各个部分
now.setFullYear(2022);
now.setMonth(11); // 设置月份为 12，注意月份是从 0 开始的，所以我们需要减 1
now.setDate(31);
now.setHours(23);
now.setMinutes(59);
now.setSeconds(59);
console.log(now); // 输出 2022-12-31 23:59:59

// 比较两个日期
let date1 = new Date(2022, 11, 31);
let date2 = new Date(2023, 0, 1);
console.log(date1 < date2); // 输出 true

// 计算两个日期之间的差异
let diff = date2.getTime() - date1.getTime();
console.log(diff); // 输出毫秒数
console.log(diff / 1000); // 输出秒数
console.log(diff / 1000 / 60); // 输出分钟数
console.log(diff / 1000 / 60 / 60); // 输出小时数
console.log(diff / 1000 / 60 / 60 / 24); // 输出天数

// 格式化日期
console.log(now.toDateString()); // 输出日期部分
console.log(now.toTimeString()); // 输出时间部分
console.log(now.toLocaleDateString()); // 输出本地格式的日期
console.log(now.toLocaleTimeString()); // 输出本地格式的时间
console.log(now.toISOString()); // 输出 ISO 格式的日期和时间