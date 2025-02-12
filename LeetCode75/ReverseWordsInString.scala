package LeetCode75

object ReverseWordsInString extends App {

  def reverseWords(s: String): String = {
//    val words = s.split("\\s+")
//    val reversedWords = words.reverse
//    reversedWords.mkString(" ")
    s.trim.split("\\s+").reverse.mkString(" ")

  }

  println(reverseWords("the sky is blue"))
  println(reverseWords("lee khan"))
  println(reverseWords("lee"))


}
