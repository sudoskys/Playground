const logger = require('pino')()

logger.info('hello world')

const child = logger.child({a: 'property'})
child.info('hello child!')

let number_exp = 1;
