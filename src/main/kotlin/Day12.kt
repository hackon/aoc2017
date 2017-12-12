fun main(args: Array<String>) {
  val connections = text(12).split("\n").map {
    val split = it.split(" <-> ")
    split[0].toInt() to split[1].split(", ").map { it.toInt() }
  }.toMap()
  part1(connections.map { mutableSetOf(it.key).apply { addAll(it.value) } })
  part2(connections.map { mutableSetOf(it.key).apply { addAll(it.value) } })
}

fun part2(connections: List<MutableSet<Int>>) {
  val list: MutableList<MutableSet<Int>> = mutableListOf()
  connections.forEachIndexed { index, connection ->
    list.firstOrNull { it.any { connection.contains(it) } }?.addAll(connection)
        ?: list.add(connected(connections, connections[index], connections.size).toMutableSet())
  }
  println(list.size)
}
private fun part1(connections: List<MutableSet<Int>>) {
  println(connected(connections, connections[0], connections.size).size)
}

tailrec fun connected(connections: List<MutableSet<Int>>, allowed: MutableSet<Int>, limit: Int) :Set<Int> =
    when (limit) {
      0 -> allowed
      else -> {
        connections
            .filter { connection -> connection.any { allowed.contains(it) } }
            .forEach { allowed.addAll(it) }
        connected(connections, allowed, limit - 1)
      }
    }