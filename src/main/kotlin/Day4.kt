fun main(args: Array<String>) {
  val passPhrases = text(4).split("\n")
  println(day4a(passPhrases))
  println(day4b(passPhrases))
}

private fun day4a(passPhrases: List<String>) =
    passPhrases.filter(::isValidPassPhrase).count()

private fun isValidPassPhrase(it: String): Boolean =
    !it.split(" ")
        .groupBy { it }
        .any { entry -> entry.value.size > 1 }

private fun day4b(passPhrases: List<String>) =
    passPhrases.filter(::isValidPassPhraseRearrange).count()

private fun isValidPassPhraseRearrange(it: String): Boolean =
    !it.split(" ")
        .map { it.toCharArray().sorted().joinToString("") }
        .groupBy { it }
        .any { entry -> entry.value.size > 1 }
