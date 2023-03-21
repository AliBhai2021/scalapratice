object StringSubSequence extends App{
  def stringSubSequence(str:String,res:String=""):Unit={
    str match {
      case x if x.isEmpty => println(res)
      case x =>
        stringSubSequence(str.substring(1),res+str(0))
        stringSubSequence(str.substring(1),res)
    }
  }

  println(stringSubSequence("abc"))
}
