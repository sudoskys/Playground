function mainJs(args1: string, args2: string, args3: string) {
    //请在此处编写代码
    /********** Begin **********/
    let a = parseInt(args1);
    console.log(a)
    let b = (parseInt(args2, 16))
    console.log(b)
    let c = parseFloat(args3);
    console.log(c)
    /********** End **********/
    return a + b + c;
}

// -12.2,ffa,1.0
let result = mainJs('-12.2', 'ffa', '1.0');
console.log(result)
