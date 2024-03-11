function mainJs(args1, args2, args3) {
    //请在此处编写代码
    /********** Begin **********/
    var a = parseInt(args1);
    console.log(a);
    var b = (parseInt(args2, 16));
    console.log(b);
    var c = parseFloat(args3);
    console.log(c);
    /********** End **********/
    return a + b + c;
}
// -12.2,ffa,1.0
var result = mainJs('-12.2', 'ffa', '1.0');
console.log(result);
