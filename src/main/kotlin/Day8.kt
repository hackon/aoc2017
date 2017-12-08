fun main(args: Array<String>) {
  val registry = mutableMapOf<String, Int>()
  val instructions = text(8).split("\n")
  var highestValue = 0
  instructions.map {
    val split = it.split(" ")
    Instruction(split[0], split[1], split[2].toInt(), Condition(split[4], split[5], split[6].toInt()))
  }
      .forEach {
        val conditionValue = registry.computeIfAbsent(it.condition.key) { 0 }
        val isConditionMet = when (it.condition.operator) {
          "==" -> {
            conditionValue == it.condition.value
          }
          "<=" -> {
            conditionValue <= it.condition.value
          }
          ">=" -> {
            conditionValue >= it.condition.value
          }
          "!=" -> {
            conditionValue != it.condition.value
          }
          "<" -> {
            conditionValue < it.condition.value
          }
          ">" -> {
            conditionValue > it.condition.value
          }
          else -> {
            throw IllegalStateException("operator ${it.condition.operator} is not handled")
          }
        }
        if (isConditionMet) {
          when (it.operation) {
            "inc" -> {
              registry.compute(it.key, { _, u -> (u ?: 0) + it.value })
            }
            "dec" -> {
              registry.compute(it.key, { _, u -> (u ?: 0) - it.value })
            }
            else -> {
              throw IllegalStateException("operator ${it.operation} is not handled")
            }
          }
        }
        val curVal = registry[it.key]?:0
        if(curVal > highestValue) highestValue = curVal
      }
  println(registry.entries.sortedByDescending { it.value }.first())
  println(highestValue)
}

data class Instruction(
    val key: String,
    val operation: String,
    val value: Int,
    val condition: Condition
)

data class Condition(
    val key: String,
    val operator: String,
    val value: Int
)
