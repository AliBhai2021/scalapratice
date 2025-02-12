package LeetCode75

object ReverseVowelsOfString extends App {
  def reverseVowels(s: String): String = {
    val vowels = Set('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    val chars = s.toCharArray
    var left = 0
    var right = s.length - 1

    while (left < right) {
      // Move left pointer to the next vowel
      while (left < right && !vowels.contains(chars(left))) {
        left += 1
      }
      // Move right pointer to the previous vowel
      while (left < right && !vowels.contains(chars(right))) {
        right -= 1
      }
      // Swap the vowels
      if (left < right) {
        val temp = chars(left)
        chars(left) = chars(right)
        chars(right) = temp
        left += 1
        right -= 1
      }
    }
    new String(chars)
    //chars.toString
  }

  println(reverseVowels("hello")) //holle
  println(reverseVowels("leetcode")) //leotcede
}
