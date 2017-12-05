class Day1

fun day1a(text: String) =
    (text + text[0]).intArray()
        .foldIndexed(0) { index, acc, value ->
          if (index > 0 && value == text[index - 1].int) acc + value else acc
        }

fun day1b(text: String) =
    text.intArray()
        .foldIndexed(0) { index, acc, value ->
          if (value == text.oppositeCircularIndex(index)) acc + value else acc
        }

private fun String.oppositeCircularIndex(index: Int) =
    this[(index + (this.length / 2)) % this.length].int



fun main(args: Array<String>) {
  val text = text(1)
  assert(day1a("1122") == 3)
  assert(day1a("1111") == 4)
  assert(day1a("1234") == 0)
  assert(day1a("91212129") == 9)
  println(day1a(text))
  assert(day1b("1212") == 6)
  assert(day1b("1221") == 0)
  assert(day1b("123425") == 4)
  assert(day1b("123123") == 12)
  assert(day1b("12131415") == 4)
  println(day1b(text))
}