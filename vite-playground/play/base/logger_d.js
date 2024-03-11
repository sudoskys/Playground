var logger = require('pino')();
logger.info('hello world');
var child = logger.child({ a: 'property' });
child.info('hello child!');
var number_exp = 1;
