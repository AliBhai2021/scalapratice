object MobileKeyBoardPatterns extends App{

  val keypad = Map(0->".",1->"abc",2->"def",3->"ghi")

  def mobileKeyPadString(str:String,res:String=""):Unit={
    str match{
      case x if x.isEmpty => println(res)
      case x =>
        for(letter <- keypad(x(0)-48))
        mobileKeyPadString(str.substring(1),res+letter)
    }
  }

  println(mobileKeyPadString("12"))
  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  println(mobileKeyPadString("123"))

}
