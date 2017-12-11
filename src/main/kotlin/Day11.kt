import kotlin.math.absoluteValue

fun main(args: Array<String>) {
  val steps = text(11).split(",")
  var coord = Coord(0,0,0)
  var max = 0
  for (step in steps) {
    when (step) {
      "n" -> coord = coord.copy(x = coord.x+1, z = coord.z-1)
      "ne" -> coord = coord.copy(x = coord.x+1, y = coord.y-1)
      "se" -> coord = coord.copy(y = coord.y-1, z = coord.z+1)
      "s" -> coord = coord.copy(x = coord.x-1, z = coord.z+1)
      "sw" -> coord = coord.copy(x = coord.x-1, y = coord.y+1)
      "nw" -> coord = coord.copy(y = coord.y+1, z = coord.z-1)
    }
    max = maxOf(max, distance(coord))

  }
  println(distance(coord))
  println(max)
}


private fun distance(coord: Coord) =
    (coord.x.absoluteValue + coord.y.absoluteValue + coord.z.absoluteValue) / 2

data class Coord(
    val x:Int,
    val y:Int,
    val z:Int
)