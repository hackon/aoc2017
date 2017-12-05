import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
  val text = text(5).split("\n")
      .map { it.toInt() }
      .toIntArray()

  assert(day5a(intArrayOf(0, 0, 1, -3)) == 5)

  println("Part1")
  println(measureNanoTime { day5a(text.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5a(text.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5a(text.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5a(text.copyOf()) }.nanoToMilli())
  assert(day5b(intArrayOf(0, 3, 0, 1, -3)) == 10)

  val text2 = text(5).split("\n")
      .map { it.toInt() }
      .toIntArray()
  println("Part2")
  println(measureNanoTime { day5b(text2.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5b(text2.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5b(text2.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5b(text2.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5b(text2.copyOf()) }.nanoToMilli())
  println(measureNanoTime { day5b(text2.copyOf()) }.nanoToMilli())

  /*
  Part1
  7.514827
  1.8242539999999998
  0.706401
  0.668338
  Part2
  107.354675
  105.21277599999999
  76.962246
  75.655845
  78.351484
  78.249998
   */
}


tailrec fun day5b(list: IntArray, index: Int = 0, step: Int = 0): Int =
    if (index >= list.size || index < 0) step
    else {
      //      list[index] = if (jump >= 3) jump - 1 else jump + 1
      day5b(list.apply { set(index, if (list[index] >= 3) list[index] - 1 else list[index] + 1) },
          index + list[index],
          step + 1)
    }

tailrec fun day5a(list: IntArray, index: Int = 0, step: Int = 0): Int =
    if (index >= list.size || index < 0) step
    else {
      val jump = list[index]
      list[index] = jump + 1
      day5a(list, index + jump, step + 1)
    }

fun Long.nanoToMilli() = this / 1000.0 / 1000.0