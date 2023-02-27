package recursionpractice

object SkipCharFromString extends App{

  println(skipChar("","baabacab"))
  println(skipAppleChar("","abcappleappledef"))

  def skipChar( s:String,str:String):String={
    str match {
      case str if str.isEmpty => s
      case str if str.charAt(0) == 'a' => skipChar(s,str.substring(1))
      case str => skipChar(s+s"${str.substring(0,1)}" , str.substring(1))

    }
  }

  def skipAppleChar( s:String,str:String):String={
    str match {
      case strl if strl.isEmpty => s
      case strl if strl.startsWith("apple") =>
        skipAppleChar(s,strl.substring(5))
      case strl =>
        skipAppleChar(s+s"${strl.substring(0,1)}" , strl.substring(1))

    }
  }

}
