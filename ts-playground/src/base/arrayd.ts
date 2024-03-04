type num_str = String | Number
let set_d = new Set<num_str>();

set_d.add(2);
set_d.add("abc");

function de(...data: any[]): void {

}

de(set_d)

console.log(set_d)

