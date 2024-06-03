let numbers: number[] = [1, 2, 3, 4, 5];

// 使用 for 循环
for (let i = 0; i < numbers.length; i++) {
    console.log(numbers[i]);
}

// 使用 for...of 循环
for (let number of numbers) {
    console.log(number);
}

// 使用 forEach 方法
numbers.forEach((number) => {
    console.log(number);
});

// map
numbers.map((number) => console.log(number * 2));

type Handler = () => void;

const handlers: Record<string, Handler> = {
    'case1': () => console.log('This is case 1'),
    'case2': () => console.log('This is case 2'),
    'default': () => console.log('This is the default case'),
};

function handleCase(caseName: string) {
    const handler = handlers[caseName] || handlers['default'];
    handler();
}

handleCase('case1'); // Output: This is case 1
handleCase('case3'); // Output: This is the default case
