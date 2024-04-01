// api/proxy.js
import {createProxyMiddleware} from 'http-proxy-middleware';

export default (req, res) => {
    let target = '';
    if (req.url.startsWith('/backend')) {
        target = 'https://api.novelai.net';
    }
    createProxyMiddleware({
        target,
        changeOrigin: true,
        pathRewrite: {
            '^/backend/': '/',
        },
    })(req, res);
};