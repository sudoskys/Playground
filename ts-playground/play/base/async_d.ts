// exp

function sleep(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}


async function asyncFunction() {
    console.log('Start');
    await sleep(2000); // 暂停 2 秒
    console.log('End');
}

asyncFunction().then(r => console.log(r));

async function handleMultipleAsyncOperations() {
    try {
        let promise1 = sleep(2); // 假设这是一个返回 Promise 的异步函数
        let promise2 = sleep(3); // 假设这是一个返回 Promise 的异步函数
        let promise3 = sleep(5); // 假设这是一个返回 Promise 的异步函数

        let results = await Promise.all([promise1, promise2, promise3]);

        console.log(results); // 这将是一个数组 [result1, result2, result3]
    } catch (error) {
        console.error("发生错误: ", error);
    }
}

handleMultipleAsyncOperations()
    .then(r => console.log(r), e => console.log(e))
    .catch(e => console.log(e)
    );

let promise = new Promise((resolve, reject) => {
    // 异步操作
    setTimeout(() => {
        if (Math.random() > 0.5) {
            resolve('成功的结果');
        } else {
            reject('失败的原因');
        }
    }, 1000);
});

promise.then(result => {
    console.log('Promise 成功: ', result);
}).catch(error => {
    console.log('Promise 失败: ', error);
});