fun main(args: Array<String>) {
  val danceMoves = text(16).split(",")
  var programs = ('a'..'p').toList().toCharArray()
  println(programs)
  partyPeople2(danceMoves, programs)
  println(programs.joinToString(""))

  programs = ('a'..'p').toList().toCharArray()
  val seen = mutableListOf<String>()
  for (i in 0 until 1000000000) {
    val s = programs.joinToString("")
    if (seen.contains(s)) {
      println("seen ${seen[1000000000%i]}")
      return
    }
    seen.add(s)
    partyPeople2(danceMoves,programs)
  }
  println(programs.joinToString(""))
}

private fun partyPeople2(danceMoves: List<String>, programs: CharArray) {
  for (danceMove in danceMoves) {
    when (danceMove[0]) {
      's' -> {
        val toInt = danceMove.drop(1).toInt()
        (programs.takeLast(toInt)+ programs.dropLast(toInt)).forEachIndexed { index, c -> programs[index] = c }
      }
      'x' -> {
        val positions = danceMove.drop(1).split("/").map { it.toInt() }
        val pA = programs[positions[0]]
        val pB = programs[positions[1]]
        programs[positions[0]] = pB
        programs[positions[1]] = pA
      }
      'p' -> {
        val partners = danceMove.drop(1).split("/").map { it[0] }
        val iA = programs.indexOf(partners[0])
        val iB = programs.indexOf(partners[1])
        programs[iA] = partners[1]
        programs[iB] = partners[0]
      }
      else -> {
      }
    }
  }
}