package lesson14

val list: List<TypeWrapper> = listOf(StringWrapper("Hi"), IntWrapper(2),
    DoubleWrapper(1.4), FloatWrapper(1.3f))

fun main() {
//    tryFilter()
    val newList = replaceItem(IntWrapper(7))
    println(newList)
}

fun tryFilter() {
    val list = listOf(1, 2, 3, 4)
    val result = filter(list, ::test)
    val result2 = filter(list) { it % 2 == 0 }
    println(result)
}

inline fun <T> filter(list: List<T>, condition: (T) -> Boolean): List<T> {
    return list.filter(condition)
}

fun test(item: Int): Boolean {
    return item % 2 == 0
}

interface TypeWrapper

data class StringWrapper(val value: String) : TypeWrapper

data class IntWrapper(val value: Int) : TypeWrapper

data class DoubleWrapper(val value: Double) : TypeWrapper

data class FloatWrapper(val value: Float) : TypeWrapper

inline fun <reified T : TypeWrapper> replaceItem(newModel: T): List<TypeWrapper> {
    val newList = list.toMutableList()
    newList.replaceAll { item ->
        if (item is T) {
            newModel
        } else {
            item
        }
    }
    return newList
}