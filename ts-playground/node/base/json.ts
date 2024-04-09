function delay(milliseconds: number): Promise<void> {
    return new Promise((resolve) => {
      setTimeout(resolve, milliseconds);
    });
}
  
delay(1000).then(() => {
    console.log('Delayed 1 second');
});

import fs from 'fs';

export function readJsonFile(filePath: string): Promise<any> {
    return fs.promises.readFile(filePath, 'utf-8').then(JSON.parse);
}

readJsonFile('myFile.json').then((data) => {
    console.log('Read JSON data:', data);
}).catch(console.error);