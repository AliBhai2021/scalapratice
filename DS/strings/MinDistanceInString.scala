package strings

object MinDistanceInString extends App {

  import scala.math._
  val s1 = "horse"
  val s2 ="ros"
  println("Min steps to replace str1 with str2 : "+ distance(s1,s1.length-1,s2,s2.length-1 ))

  def distance(str1: String, len1: Int, str2: String, len2: Int): Int = {
    (len1, len2) match {
      case (x, y) if x == 0 => y
      case (x, y) if y == 0 => x
      case (x, y) if str1(x - 1) == str2(y - 1) => distance(str1, x-1, str2, y - 1)
      case _ => {
        val insert = distance(str1, len1, str2, len2 - 1)
        val del = distance(str1, len1 - 1, str2, len2)
        val replace = distance(str1, len1 - 1, str2, len2 - 1)
        println("insert :"+insert+" del :"+del+" replace :"+replace)
        1 + min(insert, min(del, replace))
      }
    }

  }
}

