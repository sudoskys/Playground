import { v4 as uuidv4 } from 'uuid';
import crypto from 'crypto';

// 生成短 UUID
let shortUUID = uuidv4().split('-')[0];
console.log(shortUUID);

// 生成长 UUID
let longUUID = uuidv4();
console.log(longUUID);

// 生成 UUID 的 hash
let hash = crypto.createHash('sha256').update(longUUID).digest('hex');
console.log(hash);