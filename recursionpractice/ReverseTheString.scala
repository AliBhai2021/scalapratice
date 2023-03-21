object ReverseTheString extends App{

  //Method #1  recursion
  def reverseString(str:String):String={
    str match{
      case x if x.isEmpty => ""
      case x =>
          reverseString(str.substring(1))+str(0)
    }
  }
  println(reverseString("ahammed"))

  //Method #2  tail recursion
  def reverseString(str:String, res:String):String={
    str match{
      case x if x.isEmpty => res
      case x =>
        reverseString(str.substring(1),str(0)+res)
    }
  }
  println(reverseString("ahammed",""))

  //Method #3   loop
  var result=""
  val res = for(str <- "alihammed")
            yield (result = str+result)
  println("res :"+result)

}
