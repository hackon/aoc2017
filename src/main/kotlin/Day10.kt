fun main(args: Array<String>) {
  val lengths = text(10).split(",").map { it.toInt() }.toIntArray()
  println(knot(intArray255(), lengths)
      .take(2)
      .reduce { acc, i -> acc * i })

  val toIntArray = (0..4).toList().toIntArray()
  val lengths2 = listOf(3, 4, 1, 5).toIntArray()
  val knot = knot(toIntArray, lengths2)
  println(knot[0] * knot[1])

  val text = text(10)
  val day10binput = part2Input(text)
  println(knot(
      intArray255(),
      (1..64).flatMap { day10binput.toList() }.toIntArray())
      .toList()
      .chunked(16)
      .map { it.reduce { acc, number ->  acc xor number} }
      .joinToString("") { it.toString(16).padStart(2, '0') })
}

private fun intArray255() = (0..255).toList().toIntArray()

private fun part2Input(text: String): IntArray {
  return text
      .toByteArray(Charsets.US_ASCII)
      .map { it.toInt() }
      .toIntArray() + listOf(17, 31, 73, 47, 23).toIntArray()
}

tailrec fun knot(array: IntArray, lengths: IntArray, curPos: Int = 0, skipSize: Int = 0, rounds: Int = 1): IntArray =
    if (lengths.isEmpty()) array
    else {
      knot(
          array.apply {
            sliceArray((curPos..(curPos + lengths.first() - 1)).map { it % array.size })
                .reversedArray()
                .forEachIndexed { index, rvalue -> array[(curPos + index) % array.size] = rvalue }
          },
          lengths.drop(1).toIntArray(),
          (curPos + lengths.first() + skipSize) % array.size,
          skipSize + 1)
    }

