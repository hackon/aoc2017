fun text(day:Int) = SantasLittleHelper::class.java.getResource("day$day").readText()
fun String.intArray() = this.trim().toCharArray().map { it.int }
val Char.int
  get() = this.toString().toInt()
class SantasLittleHelper