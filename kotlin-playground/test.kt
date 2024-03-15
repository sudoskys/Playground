// 这是一个严谨的测试实例
// 没有其他，只有编写对语言的基本 Api 的测试

/*
这里是我第一次学 Kotlin 对语言的各种 Api 的测试
*/

// top var
var x = 114514

// sum func
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 参数传入测试
fun printSum(
    a: Int = 5,
    b: Int,
    res: String = "exp",
) {
    //返回
    println(res)
    println("sum of $a and $b is ${a + b}")
}

fun <T> asList(vararg ts: T): List<T> {
    // 定义一个空列表
    val result = ArrayList<T>()
    // 遍历传入的列表
    for (i in ts)
        result.add(i)
    return result
}


// 可以继承的
open class Shape

// 类
class Rectangle(var height: Double, var length: Double) : Shape() {
    var perimeter = (height + length) * 2
}


// 条件表达式
fun maxof(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

fun ms(a: Int, b: Int) = if (a > b) a else b


// 基本类型
fun types(a: Int):Int {
    val one = 1
    val onel: Long = 3000000000
    val byts: Byte = 127
    return one
}
fun some() {
    fun printDouble(d: Double) { print(d) }

    val i = 1
    val d = 1.0
    val f = 1.0f

    printDouble(d)
//    printDouble(i) // 错误：类型不匹配
//    printDouble(f) // 错误：类型不匹配
}

fun main() {
    // print("sum of 3 and 5 is ")
    val some = (sum(3, 5))
    println(some)
    val list = asList(1, 2, 3)
    println(list)
    println("顶层声明: $x")
    // println("sum ${sum2(10,20)}")
    printSum(5, 6, res = "someeeeee")
    // 类型
    val rectangle = Rectangle(5.0, 2.0)
    println("面积是 ${rectangle.perimeter}")
    // 替换
    var a = 200
    val str1 = "some is $a"
    a = 404
    val str2 = "${str1.replace("is", "was")},but now its $a"
    println(str2)
    println("big ：${maxof(a = 90, b = 60)}")
    println("big ：${ms(a = 90, b = 60)}")
    val items = listOf("Apple1", "Apple2", "Apple3")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("Item at $index is ${items[index]}")
    }
    val somess = "dfjalsdjfahskdjfhakjsd\nhflakjshdflkajshd"
    println(somess)

}






