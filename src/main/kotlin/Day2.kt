fun main(args: Array<String>) {
  val text = text(2)
  assert(day2a("5\t1\t9\t5") == 4)
  assert(day2a("7\t5\t3") == 4)
  assert(day2a("2\t4\t6\t8") == 4)
  println(day2a(text))
//  assert(day2b("5\t9\t2\t8") == 4)
  assert(day2b("5\t9\t2\t8") == 4)
  assert(day2b("9\t4\t7\t3") == 4)
  assert(day2b("3\t8\t6\t5") == 4)
  println(day2b(text))
}

private fun day2b(text: String): Int {
  return text.split("\n")
      .map { line ->
        line
            .split("\t")
            .map { it.toInt() }
            .sortedDescending().let { sorted ->
          sorted.foldIndexed(0) { index, acc, i ->
            when (acc) {
              0 -> sorted.drop(index + 1).filter { i % it == 0 }.map { i / it }.firstOrNull() ?: 0
              else -> acc
            }
          }
        }

      }.sum()
}

private fun day2a(text: String): Int {
  return text
      .split("\n")
      .map { line ->
        line
            .split("\t")
            .map { it.toInt() }
            .sorted()
            .let { it[it.lastIndex] - it[0] }
      }
      .sum()
}