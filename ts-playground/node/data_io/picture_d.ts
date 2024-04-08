import axios from 'axios';
import sharp from 'sharp';
import { pipeline } from 'stream';
import { promisify } from 'util';
import fs from 'fs';

const pipelineAsync = promisify(pipeline);

async function cropImage(url: string, outputPath: string) {

  const response = await axios.get(url, {
    responseType: 'stream',
    // redirect
    maxRedirects: 5,
  });

  await pipelineAsync(
    response.data,
    sharp().extract({ width: 100, height: 100, left: 10, top: 10 }),
    fs.createWriteStream(outputPath)
  );

  console.log('Image cropped.');
}


try {
   // 使用你想要的在线图片 URL
  cropImage('https://via.placeholder.com/500', 'output.jpg').catch(console.error);
} catch (error) {
  console.error(error);
}