import java.lang.Math.ceil
import java.lang.Math.sqrt

fun main(args: Array<String>) {
  val input = text(3).toInt()
  day3a(input)
  println(day3b(input))
}
fun day3b(n: Int): Int {

  val maxGrid = ceil(sqrt(n.toDouble())).toInt()

  val grid = MutableList(maxGrid, { MutableList(maxGrid, {0})})

  var x = maxGrid / 2
  var y = maxGrid / 2
  var squareSize = 2
  grid[x][y] = 1

  x++

  while (x < maxGrid && y < maxGrid) {
    for (i in y until y + squareSize) {

      val sum = grid[x+1][i] + grid[x+1][i+1] + grid[x][i+1] + grid[x-1][i+1] + grid[x-1][i] + grid[x-1][i-1] + grid[x][i-1] + grid[x+1][i-1]
      if (sum > n) {
        return sum
      }
      grid[x][i] = sum
    }

    y += squareSize - 1

    for (i in x downTo x - squareSize) {

      val sum = grid[i+1][y] + grid[i+1][y+1] + grid[i][y+1] + grid[i-1][y+1] + grid[i-1][y] + grid[i-1][y-1] + grid[i][y-1] + grid[i+1][y-1]
      if (sum > n) {
        return sum
      }
      grid[i][y] = sum
    }

    x -= squareSize

    for (i in y downTo y - squareSize) {

      val sum = grid[x+1][i] + grid[x+1][i+1] + grid[x][i+1] + grid[x-1][i+1] + grid[x-1][i] + grid[x-1][i-1] + grid[x][i-1] + grid[x+1][i-1]
      if (sum > n) {
        return sum
      }
      grid[x][i] = sum
    }

    y -= squareSize

    for (i in x .. x + squareSize) {

      val sum = grid[i+1][y] + grid[i+1][y+1] + grid[i][y+1] + grid[i-1][y+1] + grid[i-1][y] + grid[i-1][y-1] + grid[i][y-1] + grid[i+1][y-1]
      if (sum > n) {
        return sum
      }
      grid[i][y] = sum
    }

    x += squareSize + 1
    squareSize += 2
  }

  return 0
}

private fun day3a(input: Int) {
  val found = 0
  var src = 1
  var min = 1
  var max = 1
  var counter = 0
  while (found == 0) {
    if (input in (min + 1)..(max - 1)) {
      break
    }
    min = max
    counter++
    src += 2
    max = src.pow(2)
  }
  println(src)
  println(counter)

  val decounter = src - 1
  var init = counter
  var p = 0
  val da = IntArray(4)
  while (p != 4) {
    da[p] = src.pow(2) - init
    init += decounter
    p++
  }
  val ra = IntArray(4)
  da.forEachIndexed { index, i ->
    var res = i - input
    if (res < 0)
      res *= -1
    ra[index] = res
  }
  println(ra.min())
  println("Distance: ${ra.min()!! + counter}")
}

fun Int.pow(value:Int) = Math.pow(this.toDouble(), value.toDouble()).toInt()