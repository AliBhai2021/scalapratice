package recursion

object MobileKeyBoardPatterns extends App{

  val keypad = Map(0->".",1->"abc",2->"def",3->"ghi")
  def mobileKeyPadString(keys:String,result:String=""):Unit={
    keys match {
      case key if key.isEmpty => println(result)
      case key =>
        for(letter <- keypad(key(0)-48))
          mobileKeyPadString(key.substring(1),result+letter)

    }
  }

  println(mobileKeyPadString("12"))
  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  println(mobileKeyPadString("123"))

}
