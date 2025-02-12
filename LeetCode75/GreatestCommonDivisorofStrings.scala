package LeetCode75

object GreatestCommonDivisorofStrings extends App{
  def gcdOfStrings(str1: String, str2: String): String = {
    (str1+str2, str2+str1) match{
      case (x,y) if x != y => ""
      case (x,y)=>
        val strlength = gcd(str1.length,str2.length)
        str1.substring(0, strlength)
    }
  }

  def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  println(gcdOfStrings("ABCABC", "ABC"))
  println(gcdOfStrings("ABABAB",  "ABAB"))
}
