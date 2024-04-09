import { error } from 'console';
// 操作各种数据库
// https://github.com/redis/ioredis
import Redis from 'ioredis';
import dotenv from 'dotenv';

// Load environment variables from .env file
dotenv.config();

const redis = new Redis(
    {  
    host: process.env.REDIS_HOST || '127.0.0.1',
    port: Number(process.env.REDIS_PORT) || 6379,
    retryStrategy(times) {
        const delay = Math.min(times * 50, 2000);
        return delay;
    },
    maxRetriesPerRequest: 5,
    }
);

// Now you can use the loaded environment variables
console.log(process.env.MY_VARIABLE);
async function checkRedis() {
  try {
    await redis.ping();
    console.log('Redis is running.');
  } catch (error) {
    console.error('Failed to connect to Redis:', error);
    process.exit(1); // Exit with a failure code
  }
}


// Set a value in Redis
async function setValue(key: string, value: string): Promise<void> {
    await redis.set(key, value);
}

// Get a value from Redis
async function getValue(key: string): Promise<string | null> {
    return await redis.get(key);
}

// Example usage
async function main() {
    await setValue('myKey', 'myValue');
    const value = await getValue('myKey');
    console.log(value); // Output: myValue
    redis.disconnect();
    redis.quit();
}
checkRedis().catch(error=>{
    console.error(error);
    process.exit(1);
}).then(()=>{
    main().catch(console.error);
});
process.on('beforeExit', async () => {
    console.log('Closing resources...');
    await closeAllResources();
    console.log('Resources closed.');
  });
  
  process.on('SIGINT', async () => {
    console.log('Received SIGINT. Closing resources...');
    await closeAllResources();
    console.log('Resources closed.');
    process.exit(0);
  });
  
  async function closeAllResources() {
    // Close your resources here, for example:
    // await redis.quit();
  }
/*
箭头函数除了语法更简洁之外，还有一个重要的特性，就是它不会创建自己的 `this`，`this` 的值会从定义箭头函数的上下文中继承。这在处理事件回调或者 Promise 的回调时非常有用。
 */