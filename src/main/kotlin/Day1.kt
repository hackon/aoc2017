object Day1 {
  fun part1(text: String) =
      text.let {
        (it + it[0]).toCharArray()
            .map { it.toString().toInt() }
            .foldIndexed(0) { index, acc, value ->
              if (index > 0 && value == it[index - 1].toString().toInt()) acc + value else acc
            }
      }

  fun part2(text: String) =
      text.toCharArray()
          .map { it.toString().toInt() }
          .foldIndexed(0) { index, acc, value ->
            if (value == text[(index + (text.length / 2)) % text.length].toString().toInt()) acc + value else acc
          }
}

fun main(args: Array<String>) {
  val text = Day1::class.java.getResource("day1").readText()
  assert(Day1.part1("1122") == 3)
  assert(Day1.part1("1111") == 4)
  assert(Day1.part1("1234") == 0)
  assert(Day1.part1("91212129") == 9)
  println(Day1.part1(text))
  assert(Day1.part2("1212") == 6)
  assert(Day1.part2("1221") == 0)
  assert(Day1.part2("123425") == 4)
  assert(Day1.part2("123123") == 12)
  assert(Day1.part2("12131415") == 4)
  println(Day1.part2(text))
}