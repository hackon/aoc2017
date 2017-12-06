
fun main(args: Array<String>) {
  val memory = text(6).split("\t").map { it.toInt() }.toIntArray()
  day6a(memory)
  print(memRedis2(memory, listOf(memory.joinToString(""))))
}

private fun day6a(memory: IntArray) {
  val snapshots = mutableListOf<String>()
  println(memRedis(memory, snapshots))
}

tailrec fun memRedis(memory: IntArray, snapshots: MutableList<String>, steps: Int = 0): Int =
    if (snapshots.contains(memory.joinToString("")))
      steps
    else {
      snapshots.add(memory.joinToString(""))
      val (index, value) = findMax(memory)
      memory[index] = 0
      for (step in (1..value)) {
        memory[(index + step) % memory.size]++
      }
      memRedis(memory, snapshots, steps + 1)
    }
tailrec fun memRedis2(memory: IntArray, snapshots: List<String>, steps: Int = 0): Int =
    if (steps != 0 && snapshots.contains(memory.joinToString("")))
      steps
    else {
      val (index, value) = findMax(memory)
      memory[index] = 0
      for (step in (1..value)) {
        memory[(index + step) % memory.size]++
      }
      memRedis2(memory, snapshots, steps + 1)
    }

fun findMax(memory: IntArray): Pair<Int, Int> {
  val max = memory.max()!!
  val indexOf = memory.indexOf(max)
  return indexOf to max
}