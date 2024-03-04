mkdir ~/.npm-global
npm config set prefix '~/.npm-global'
export PATH=~/.npm-global/bin:$PATH

npm install ts-node
npm install webpack webpack-cli
npm install ts-loader source-map-loader
npm install --save react react-dom @types/react @types/react-dom
npm install --save-dev typescript awesome-typescript-loader source-map-loader
