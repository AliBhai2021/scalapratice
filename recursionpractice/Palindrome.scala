object Palindrome extends App{

  def isPalindrome(str:String):Boolean={
    str match {
      case x if x.isEmpty | x.length==1 => true
      case x if x(0) == x(x.length-1) =>
        isPalindrome(x.substring(1,x.length-1))
      case _ => false
    }
  }
  println(isPalindrome("ali"))
  println(isPalindrome("akxka"))
  println(isPalindrome("abcxyyyxcba"))

  println("abcdefg".substring(1,"abcdefg".length-1))

}
