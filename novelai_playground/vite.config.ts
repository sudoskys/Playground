import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react-swc'
import UnoCSS from 'unocss/vite'

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        proxy: {
            '/backend': {
                target: 'https://api.novelai.net',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/backend/, '')
            }
        }
    },
    plugins: [
        react(),
        UnoCSS(),
    ],
})
