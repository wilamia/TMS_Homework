fun main() {
//    createPerson()
//    printInfoAboutBook()
//    newListWithSqr()
//    filterNumbers()
//    sequenceNumbers()
//    createSequence()
//    sunOfNumbers()
//    vowelsInString()
//    getAverageValue()
//    checkIsEven()
//    getSumOfNumbers()
//    getFilterPerson()
    createListOfNumbers()
}

/*Создайте объект класса Person с полем name, используя apply.
Затем выведите имя через let.*/

fun createPerson() {
    val person = Person().apply {
        name = "Антон"
    }
    person.let {
        println(it.name)
    }
}

/* Напишите код, который создает объект класса Book и выводит
информацию о нем, используя run.*/

fun printInfoAboutBook() {
    val book = Book("Pride and Prejudice", 1813, "Jane Austen")
    book.run {
        println("Title: $title")
        println("Year: $year")
        println("Author: $author")
    }
}

/* Используя map, создайте новый список строк, где каждая строка — это
строковое представление квадратов чисел из списка.*/

fun newListWithSqr() {
    val numbers = listOf(1, 3, 6, 34, 86, 2, 9, 23)
    val squares = numbers.map { it * it }
    println(squares)
}

/*Используя filter, создайте новый список, содержащий только четные
числа из исходного списка.*/

fun filterNumbers() {
    val numbers = listOf(1, 3, 6, 34, 86, 2, 9, 23)
    val newList = numbers.filter { it % 2 == 0 }
    println(newList)
}

/* Используя asSequence, выполните последовательную фильтрацию и
возведение в квадрат для списка четных чисел, но только после того как вы
преобразуете его в Sequence.*/

fun sequenceNumbers() {
    val numbers = listOf(1, 3, 6, 34, 86, 2, 9, 23)
    val newList = numbers.asSequence().filter { it % 2 == 0 }.map { it * it }.toList()
    println(newList)
}

/*Используя sequenceOf, создайте последовательность, содержащую
квадратные числа от 1 до 5.*/

fun createSequence() {
    val list = sequenceOf(1, 2, 3, 4, 5).map { it * it }.toList()
    println(list)
}

/* Создайте функцию inline, которая принимает два числа и выполняет
операцию сложения через лямбду.*/

inline fun performOperation(firstNumber: Int, secondNumber: Int, operation: (Int, Int) -> Int): Int {
    return operation(firstNumber, secondNumber)
}

fun sunOfNumbers() {
    val sum = performOperation(10, 12) { x, y ->
        x + y
    }
    println(sum)
}

/* Создайте расширение для типа String, которое возвращает
количество гласных в строке.*/

fun String.countOfVowels(): Int {
    val vowels = "aeiouAEIOU"
    return this.count { it in vowels }
}

fun vowelsInString() {
    val text = "Hello world!"
    println("Количество гласных: ${text.countOfVowels()}")
}

/* Напишите расширение для List<Int>, которое возвращает среднее
значение.*/

fun List<Int>.averageValue(): Int {
    return this.sumOf { it } / this.size
}

fun getAverageValue() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Среднее значение: ${list.averageValue()}")
}

/*Напишите расширение для типа Int, которое возвращает true, если
число четное, и false, если нечетное.*/

fun Int.isEven(): Boolean {
    return this % 2 == 0
}

fun checkIsEven() {
    val num1 = 5
    val num2 = 4
    if (num1.isEven()) {
        println("Первое число четное")
    } else {
        println("Первое число нечетное")
    }

    if (num2.isEven()) {
        println("Второе число четное")
    } else {
        println("Второе число нечетное")
    }
}

/* Напишите функцию, которая принимает список целых чисел и
возвращает их сумму. Используйте лямбду в качестве параметра для
определения операции. (Reduce)*/

fun sumOfNumbers(numbers: List<Int>): Int {
    return numbers.reduce { acc, num -> acc + num }
}

fun getSumOfNumbers() {
    val numbers = listOf(1, 2, 3, 4, 5)
    val sum = sumOfNumbers(numbers)
    println("Сумма чисел: $sum")
}

/*Напишите программу, которая получает список людей, фильтрует
только тех, кто старше 18 лет, затем сортирует их по возрасту в порядке
убывания и выводит на экран с помощью apply и let. (sortedByDescending)*/

fun filterPerson(list: List<People>) {
    list.asSequence().filter { it.age >= 18 }
        .sortedByDescending { it.age }
        .apply {
            println("Люди старше 18 лет, отсортированные по возрасту:")
        }
        .forEach { person ->
            person.let {
                println("${it.name}, возраст: ${it.age}")
            }
        }
}

fun getFilterPerson() {
    val people = listOf(
        People("Alice", 25),
        People("Bob", 17),
        People("Charlie", 30),
        People("David", 15),
        People("Eve", 22)
    )
    filterPerson(people)
}

/* Создайте список чисел от 1 до 1,000,000, отфильтруйте четные числа,
затем возведите их в квадрат и посчитайте сумму всех этих чисел. Сделайте это с
использованием asSequence для ленивой обработки.*/

fun createListOfNumbers() {
    val sum = (1..1_000_000).asSequence()
        .filter { it % 2 == 0 }
        .map { it.toLong() * it }
        .sum()
    println(sum)
}