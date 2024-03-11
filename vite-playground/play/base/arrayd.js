var set_d = new Set();
set_d.add(2);
set_d.add("abc");
function de() {
    var data = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        data[_i] = arguments[_i];
    }
    console.log(data);
}
de(set_d);
console.log(set_d);
