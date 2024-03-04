// const logger = require('pino')()

// Path: src/logger/index.ts

export class logger<T = any> {
    info(data: T): null {
        console.log(data)
        return null
    }
}