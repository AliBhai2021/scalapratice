package LeetCode75

object MergeStringsAlternately extends App {

  def mergeAlternately(word1: String, word2: String): String = {
    val zipped = word1.zip(word2).flatMap { case (c1, c2) => Seq(c1, c2) }
    val remaining = if (word1.length > word2.length)
      word1.drop(word2.length)
    else
      word2.drop(word1.length)

    println("Zipped = "+zipped)
    println("remaining = "+remaining)
    (zipped ++ remaining).mkString
  }

  println(mergeAlternately("ali","xyz"))

}
